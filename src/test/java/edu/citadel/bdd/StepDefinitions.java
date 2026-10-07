package edu.citadel.bdd;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class StepDefinitions {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    private ResponseEntity<String> response;
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
    public void i_send_a_get_request_to(String path) {
        response = restTemplate.getForEntity(baseUrl() + path, String.class);
    }

    @When("I send a POST request to {string}")
    public void i_send_a_post_request_to(String path) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> entity = new HttpEntity<>(requestBody, headers);
        response = restTemplate.postForEntity(baseUrl() + path, entity, String.class);

        if (response.getStatusCode().value() == 201 && response.getBody() != null) {
            String body = response.getBody();
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
    public void i_send_a_get_request_to_the_created_accounts_id_endpoint() {
        response = restTemplate.getForEntity(
                baseUrl() + "/account/" + createdAccountId, String.class);
    }

    @Then("the response status code should be {int}")
    public void the_response_status_code_should_be(int statusCode) {
        assertEquals(statusCode, response.getStatusCode().value());
    }

    @Then("the response body should contain {string}")
    public void the_response_body_should_contain(String expected) {
        assertTrue(response.getBody() != null && response.getBody().contains(expected),
                "Expected response body to contain \"" + expected + "\" but was: " + response.getBody());
    }
}
