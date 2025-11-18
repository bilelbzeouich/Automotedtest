@webForm
Feature: Web Form Submission

  Scenario: Submit web form with valid data
    Given the user is on the web form page
    When the user enters text "Test Selenium" in the text field
    And the user enters password "secret123" in the password field
    And the user enters "This is a test message" in the textarea
    And the user selects "1" from the dropdown
    And the user enters date "2024-12-31" in the date picker
    And the user checks the checkbox
    And the user clicks the radio button
    And the user clicks the submit button
    Then the user should see a success message containing "Received"