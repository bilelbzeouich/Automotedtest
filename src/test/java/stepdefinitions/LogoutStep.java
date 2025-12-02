package stepdefinitions;

import base.TestBase;
import com.aventstack.extentreports.Status;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import org.junit.jupiter.api.Assertions;
import pages.LoginPage;
import pages.LogoutPage;

public class LogoutStep {

    private final LogoutPage logoutPage;
    private final LoginPage loginPage;

    public LogoutStep() {
        this.logoutPage = new LogoutPage(TestBase.getDriver());
        this.loginPage = new LoginPage(TestBase.getDriver());
    }

    @Given("the user is logged in with username {string} and password {string}")
    public void the_user_is_logged_in_with_username_and_password(String username, String password) {
        try {
            loginPage.openLoginPage();
            loginPage.enterUsername(username);
            loginPage.enterPassword(password);
            loginPage.clickLoginButton();

            // Verify login was successful
            String successMessage = loginPage.getSuccessMessage();
            String cleanedMessage = successMessage.replace("×", "").trim();
            Assertions.assertTrue(cleanedMessage.contains("You logged into a secure area"),
                    "Login failed. Cannot proceed with logout test.");

            Hooks.scenario.log(Status.PASS, "User successfully logged in with username: " + username);
        } catch (Exception e) {
            String errorDetails = "Failed to login before logout test\n" +
                    "Error: " + e.getMessage() + "\n" +
                    "Cause: " + (e.getCause() != null ? e.getCause().getMessage() : "N/A") + "\n" +
                    "Stack Trace: " + getStackTrace(e);
            Hooks.scenario.log(Status.FAIL, errorDetails);
            throw e;
        }
    }

    @When("the user clicks on the logout button")
    public void the_user_clicks_on_the_logout_button() {
        try {
            logoutPage.clickLogoutButton();
            Hooks.scenario.log(Status.PASS, "Logout button clicked");
        } catch (Exception e) {
            String errorDetails = "Failed to click logout button\n" +
                    "Error: " + e.getMessage() + "\n" +
                    "Cause: " + (e.getCause() != null ? e.getCause().getMessage() : "N/A") + "\n" +
                    "Stack Trace: " + getStackTrace(e);
            Hooks.scenario.log(Status.FAIL, errorDetails);
            throw e;
        }
    }

    @Then("the user should see a successful logout message")
    public void the_user_should_see_a_successful_logout_message() {
        try {
            String logoutMessage = logoutPage.getLogoutMessage();
            System.out.println("Message de déconnexion: " + logoutMessage);

            // Clean the message to remove any special characters like ×
            String cleanedMessage = logoutMessage.replace("×", "").trim();

            Assertions.assertTrue(cleanedMessage.contains("You logged out of the secure area"),
                    "Le message de déconnexion ne contient pas le texte attendu. Message actuel: '" + logoutMessage
                            + "'");

            Hooks.scenario.log(Status.PASS, "Successful logout message displayed: " + logoutMessage);
        } catch (AssertionError e) {
            String errorDetails = "Assertion Failed: " + e.getMessage() + "\n" +
                    "Expected: Message should contain 'You logged out of the secure area'\n" +
                    "Stack Trace: " + getStackTrace(e);
            Hooks.scenario.log(Status.FAIL, errorDetails);
            throw e;
        } catch (Exception e) {
            String errorDetails = "Failed to verify successful logout message\n" +
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
