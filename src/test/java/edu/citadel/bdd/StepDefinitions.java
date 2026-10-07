package edu.citadel.bdd;

import edu.citadel.dal.AccountRepository;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.server.LocalServerPort;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class StepDefinitions {

    @LocalServerPort
    private int port;

    @Autowired
    private AccountRepository accountRepository;

    @Before
    public void cleanDatabase() {
        accountRepository.deleteAll();
    }

    private final HttpClient httpClient = HttpClient.newHttpClient();
    private HttpResponse<String> response;
    private String requestBody;
    private Long createdAccountId;

    private String baseUrl() {
        return "http://localhost:" + port;
    }

    @Given("I have an account request with username {string}, password {string}, and email {string}")
    public void i_have_an_account_request(String username, String password, String email) {
        requestBody = String.format(
                "{\"username\":\"%s\",\"password\":\"%s\",\"email\":\"%s\"}",
                username, password, email);
    }

    @When("I send a GET request to {string}")
    public void i_send_a_get_request_to(String path) throws Exception {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(baseUrl() + path))
                .GET()
                .build();
        response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
    }

    @When("I send a POST request to {string}")
    public void i_send_a_post_request_to(String path) throws Exception {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(baseUrl() + path))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(requestBody))
                .build();
        response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

        if (response.statusCode() == 201 && response.body() != null) {
            String body = response.body();
            int idIndex = body.indexOf("\"user_id\"");
            if (idIndex >= 0) {
                String afterKey = body.substring(idIndex + 9);
                afterKey = afterKey.replaceFirst("^\\s*:\\s*", "");
                StringBuilder idStr = new StringBuilder();
                for (char c : afterKey.toCharArray()) {
                    if (Character.isDigit(c)) {
                        idStr.append(c);
                    } else if (idStr.length() > 0) {
                        break;
                    }
                }
                if (idStr.length() > 0) {
                    createdAccountId = Long.parseLong(idStr.toString());
                }
            }
        }
    }

    @When("I send a GET request to the created account's ID endpoint")
    public void i_send_a_get_request_to_the_created_accounts_id_endpoint() throws Exception {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(baseUrl() + "/account/" + createdAccountId))
                .GET()
                .build();
        response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
    }

    @Then("the response status code should be {int}")
    public void the_response_status_code_should_be(int statusCode) {
        assertEquals(statusCode, response.statusCode());
    }

    @Then("the response body should contain {string}")
    public void the_response_body_should_contain(String expected) {
        assertTrue(response.body() != null && response.body().contains(expected),
                "Expected response body to contain \"" + expected + "\" but was: " + response.body());
    }
}
