package stepdefinitions;

import base.TestBase;
import com.aventstack.extentreports.Status;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import org.junit.jupiter.api.Assertions;
import pages.AddEmployeePage;
import pages.LoginPage;

public class PIMStep {

    private final AddEmployeePage addEmployeePage;
    private final LoginPage loginPage;

    public PIMStep() {
        this.addEmployeePage = new AddEmployeePage(TestBase.getDriver());
        this.loginPage = new LoginPage(TestBase.getDriver());
    }

    @Given("the user is logged in and navigates to PIM Add Employee page")
    public void the_user_is_logged_in_and_navigates_to_pim_add_employee_page() {
        try {
            // Check if user is already logged in
            boolean isAlreadyLoggedIn = addEmployeePage.isUserLoggedIn();

            if (!isAlreadyLoggedIn) {
                // User is not logged in, perform login
                loginPage.openLoginPage();
                loginPage.enterUsername("Admin");
                loginPage.enterPassword("admin123");
                loginPage.clickLoginButton();

                // Wait for login to complete - wait for dashboard to appear
                try {
                    addEmployeePage.isUserLoggedIn();
                } catch (Exception e) {
                    // Continue - navigation will handle waiting
                }

                Hooks.scenario.log(Status.PASS, "User logged in successfully");
            } else {
                Hooks.scenario.log(Status.PASS, "User is already logged in, skipping login");
            }

            // Navigate to Add Employee page
            addEmployeePage.navigateToAddEmployee();

            // Wait for the page to fully load - isAddEmployeePageDisplayed uses explicit
            // wait
            boolean isDisplayed = addEmployeePage.isAddEmployeePageDisplayed();
            Assertions.assertTrue(isDisplayed, "Add Employee page is not displayed");

            Hooks.scenario.log(Status.PASS, "User navigated to the PIM Add Employee page");
        } catch (Exception e) {
            String errorDetails = "Failed to navigate to Add Employee page\n" +
                    "Error: " + e.getMessage() + "\n" +
                    "Cause: " + (e.getCause() != null ? e.getCause().getMessage() : "N/A") + "\n" +
                    "Stack Trace: " + getStackTrace(e);
            Hooks.scenario.log(Status.FAIL, errorDetails);
            throw e;
        }
    }

    @When("the user enters employee first name as {string}")
    public void the_user_enters_employee_first_name_as(String firstName) {
        try {
            addEmployeePage.enterFirstName(firstName);
            Hooks.scenario.log(Status.PASS, "Employee first name entered: " + firstName);
        } catch (Exception e) {
            String errorDetails = "Failed to enter employee first name: " + firstName + "\n" +
                    "Error: " + e.getMessage() + "\n" +
                    "Cause: " + (e.getCause() != null ? e.getCause().getMessage() : "N/A") + "\n" +
                    "Stack Trace: " + getStackTrace(e);
            Hooks.scenario.log(Status.FAIL, errorDetails);
            throw e;
        }
    }

    @When("the user enters employee middle name as {string}")
    public void the_user_enters_employee_middle_name_as(String middleName) {
        try {
            addEmployeePage.enterMiddleName(middleName);
            Hooks.scenario.log(Status.PASS, "Employee middle name entered: " + middleName);
        } catch (Exception e) {
            String errorDetails = "Failed to enter employee middle name: " + middleName + "\n" +
                    "Error: " + e.getMessage() + "\n" +
                    "Cause: " + (e.getCause() != null ? e.getCause().getMessage() : "N/A") + "\n" +
                    "Stack Trace: " + getStackTrace(e);
            Hooks.scenario.log(Status.FAIL, errorDetails);
            throw e;
        }
    }

    @When("the user enters employee last name as {string}")
    public void the_user_enters_employee_last_name_as(String lastName) {
        try {
            addEmployeePage.enterLastName(lastName);
            Hooks.scenario.log(Status.PASS, "Employee last name entered: " + lastName);
        } catch (Exception e) {
            String errorDetails = "Failed to enter employee last name: " + lastName + "\n" +
                    "Error: " + e.getMessage() + "\n" +
                    "Cause: " + (e.getCause() != null ? e.getCause().getMessage() : "N/A") + "\n" +
                    "Stack Trace: " + getStackTrace(e);
            Hooks.scenario.log(Status.FAIL, errorDetails);
            throw e;
        }
    }

    @When("the user clicks on the Save button to add employee")
    public void the_user_clicks_on_the_save_button_to_add_employee() {
        try {
            addEmployeePage.clickSaveButton();
            Hooks.scenario.log(Status.PASS, "Save button clicked to add employee");
        } catch (Exception e) {
            String errorDetails = "Failed to click Save button\n" +
                    "Error: " + e.getMessage() + "\n" +
                    "Cause: " + (e.getCause() != null ? e.getCause().getMessage() : "N/A") + "\n" +
                    "Stack Trace: " + getStackTrace(e);
            Hooks.scenario.log(Status.FAIL, errorDetails);
            throw e;
        }
    }

    @Then("the user should see a success message for employee creation")
    public void the_user_should_see_a_success_message_for_employee_creation() {
        try {
            // Wait for the save to complete - getSuccessMessage uses explicit waits
            String successMessage = addEmployeePage.getSuccessMessage();

            // Verify that a success message is present
            Assertions.assertNotNull(successMessage, "Success message should not be null");
            Assertions.assertFalse(successMessage.isEmpty(), "Success message should not be empty");

            Hooks.scenario.log(Status.PASS, "Success message for employee creation: " + successMessage);
        } catch (AssertionError e) {
            String errorDetails = "Assertion Failed: " + e.getMessage() + "\n" +
                    "Stack Trace: " + getStackTrace(e);
            Hooks.scenario.log(Status.FAIL, errorDetails);
            throw e;
        } catch (Exception e) {
            String errorDetails = "Failed to verify success message for employee creation\n" +
                    "Error: " + e.getMessage() + "\n" +
                    "Cause: " + (e.getCause() != null ? e.getCause().getMessage() : "N/A") + "\n" +
                    "Stack Trace: " + getStackTrace(e);
            Hooks.scenario.log(Status.FAIL, errorDetails);
            throw e;
        }
    }

    private String getStackTrace(Throwable e) {
        java.io.StringWriter sw = new java.io.StringWriter();
        java.io.PrintWriter pw = new java.io.PrintWriter(sw);
        e.printStackTrace(pw);
        return sw.toString();
    }
}
