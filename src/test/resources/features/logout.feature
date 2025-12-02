@LogoutTests
Feature: Logout

  Background:
    Given the user is logged in with username "tomsmith" and password "SuperSecretPassword!"

  @Logout
  Scenario: Successful logout
    When the user clicks on the logout button
    Then the user should see a successful logout message

