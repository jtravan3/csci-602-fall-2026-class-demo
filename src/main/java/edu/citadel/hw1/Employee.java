package edu.citadel.hw1;

import java.time.LocalDate;

public abstract class Employee implements Comparable<Employee> {

  private String type;
  private LocalDate hireDate;

  public Employee(String type, LocalDate hireDate) {
    this.type = type;
    this.hireDate = hireDate;
  }

  public String getType() {
    return type;
  }

  public LocalDate getHireDate() {
    return hireDate;
  }

  abstract double getMonthlyPay();

  @Override
  public int compareTo(Employee o) {
    return Double.compare(o.getMonthlyPay(), this.getMonthlyPay());
  }
}
