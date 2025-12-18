@PIMTests
Feature: PIM - Add Employee

  Background:
    Given the user is on the login page
    When the user enters a username as "Admin"
    And the user enters a password as "admin123"
    And clicks on the login button
    Then the user should see a successful login message

  @AddEmployee
  Scenario: Add new employee via PIM
    Given the user is logged in and navigates to PIM Add Employee page
    When the user enters employee first name as "Alice"
    And the user enters employee middle name as "Marie"
    And the user enters employee last name as "Johnson"
    And the user clicks on the Save button to add employee
    Then the user should see a success message for employee creation

