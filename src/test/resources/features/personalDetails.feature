@PersonalDetailsTests
Feature: Personal Details - Change Account Full Name

  Background:
    Given the user is on the login page
    When the user enters a username as "Admin"
    And the user enters a password as "admin123"
    And clicks on the login button
    Then the user should see a successful login message
    And the user is logged in and on the personal details page

  @UpdateFullName
  Scenario Outline: Change account full name and verify
    When the user clicks on the Edit button
    And the user changes profile name to first name "<firstName>", middle name "<middleName>", and last name "<lastName>"
    And the user clicks on the Save button
    Then the user should see a success message
    And the profile name should be "<expectedFirstName>" "<expectedMiddleName>" "<expectedLastName>"

    Examples:
      | firstName | middleName | lastName | expectedFirstName | expectedMiddleName | expectedLastName |
      | John      | Michael    | Smith    | John              | Michael            | Smith           |

