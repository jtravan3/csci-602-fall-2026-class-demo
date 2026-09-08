
# HW 1 - Learning Java

## Problem Description

**Create the following three classes (all classes need to be created within the `hw1` package):**

Class `Employee`
* Abstract class that implements `Comparable<Employee>`.
* Private fields name `(type String)` and `hireDate` (type `LocalDate`)
* Public constructor that initializes both fields
* Public `get()` methods for both fields
* Abstract method `getMonthlyPay()` that returns a double.
* Method `compareTo()` as required by interface `Comparable`. This method should order employees based on
their monthly salaries. So, for example, if the monthly salary for `emp1` is less than the monthly salary for
`emp2`, then `emp1.compareTo(emp2)` should return a negative number.

Class `HourlyEmployee`
* Subclass of `Employee`
* Private fields `wageRate` (type `double`) and `hoursWorked` (type `double`)
* Public constructor that initializes all four fields (two declared in this class and two inherited from class
`Employee`)
* Public `get()` methods for both fields declared in this class
* Public method `getMonthlyPay()` that overrides the method from the superclass
    * For this class monthly pay is computed as `wageRate` times hoursWorked
* Public method `toString()` that returns a string of the form
`"HourlyEmployee[name=John Doe, hireDate=2009-05-21, wageRate=50.5, hoursWorked=160.0]"`
* Public methods `hashCode()` and `equals()`

Class `SalariedEmployee`
* Subclass of `Employee`
* Private field `annualSalary` (type `double`)
* Public constructor that initializes all three fields (one declared in this class and two inherited from class
`Employee`)
* Public `get()` method for the field declared in this class
* Public method `getMonthlyPay()` that overrides the method from the superclass
    * For this class monthly pay is computed as `annualSalary` divided by 12.0
* Public method `toString()` that returns a string of the form
`"SalariedEmployee[name=Curly Howard, hireDate=2009-05-21, annualSalary=105000.0]"`
* Public methods `hashCode()` and `equals()`

**Next, create a tester class named InheritanceDemo. Class `InheritanceDemo` contains method main() that
performs the following:**

* Create an `ArrayList` for `Employee`
* Create two hourly employees as follows as follows and add them to the list
    * name=John Doe, hireDate=2009-05-21, wageRate=50.5, hourWorked=160.0
    * name=Jane Doe, hireDate=2005-09-01, wageRate=150.5, hourWorked=80.0
* Create two salaried employees as follows and add them to the list
    * name=Moe Howard, hireDate=2004-01-01, annualSalary=75000.0
    * name=Curly Howard, hireDate=2018-01-01, annualSalary=105000.0
* Print “`List of Employees (before sorting)`” followed by a listing of each employee, one per employee
per line. Employees should be written in the order that the employee was added to the list.
* Print out a blank line.
* Using class `Collections` from `java.util, call Collections.sort()` passing the list of employees as an
argument. This will use your `compareTo()` method to sort the list based on monthly pay, so that employees
with lower monthly pay will be in the list before those with higher monthly pay.
* Print “`List of Employees (after sorting)`” followed by a listing of each employee, one per employee
per line. Employees should now be written in the sorted order. (The order of the listing will not be obvious
at this point, but it will become more meaningful after the next few steps where we print the monthly pay for
each employee.)
* Print out a blank line.
* Print “`Monthly Pay`” followed by a listing of the monthly pay for each employee followed by the total
monthly pay for all employees
    * Print the monthly pay for each employee using code similar to the following:
    `printf("%s: $%,.2f\n", employee.getName(), employee.getMonthlyPay())`
    * Print the total pay for all employees using code similar to the following:
    `printf("Total Monthly Pay: $%,.2f", totalPay)`

## Sample Output

```text
List of Employees (before sorting)
HourlyEmployee[name=John Doe, hireDate=2009-05-21, wageRate=50.5, hoursWorked=160.0]
HourlyEmployee[name=Jane Doe, hireDate=2005-09-01, wageRate=150.5, hoursWorked=80.0]
SalariedEmployee[name=Moe Howard, hireDate=2004-01-01, annualSalary=75000.0]
SalariedEmployee[name=Curly Howard, hireDate=2018-01-01, annualSalary=105000.0]

List of Employees (after sorting)
SalariedEmployee[name=Moe Howard, hireDate=2004-01-01, annualSalary=75000.0]
HourlyEmployee[name=John Doe, hireDate=2009-05-21, wageRate=50.5, hoursWorked=160.0]
SalariedEmployee[name=Curly Howard, hireDate=2018-01-01, annualSalary=105000.0]
HourlyEmployee[name=Jane Doe, hireDate=2005-09-01, wageRate=150.5, hoursWorked=80.0]

Monthly Pay
Moe Howard: $6,250.00
John Doe: $8,080.00
Curly Howard: $8,750.00
Jane Doe: $12,040.00
Total Monthly Pay: $35,120.00
```

## Submission Instructions:

1. Finish the assignment from the instructions above
2. Commit your code to your repository

## Total Points: 100

## Grading Rubric

|               |  5  |  4  |  3  |  2  |  1  | Points |
|---------------|:---:|:---:|:---:|:---:|:---:|:------:|
| Coding Style  |     |     |     |     |     |        |
| Accuracy      |     |     |     |     |     |        |
| Maintain      |     |     |     |     |     |        |
| Efficiency    |     |     |     |     |     |        |

## Overall Grade

| Letter Grade   | Points |
|:--------------:|:------:|
|     -          |   -    |
