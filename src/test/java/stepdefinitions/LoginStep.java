package stepdefinitions;

import base.TestBase;
import com.aventstack.extentreports.Status;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.And;
import org.junit.jupiter.api.Assertions;
import pages.LoginPage;

public class LoginStep {

    private final LoginPage loginPage;

    public LoginStep() {
        this.loginPage = new LoginPage(TestBase.getDriver());
    }

    @Given("the user is on the login page")
    public void userIsOnLoginPage() {
        try {
            loginPage.openLoginPage();
            Hooks.scenario.log(Status.PASS, "The user is on the login page");
        } catch (Exception e) {
            Hooks.scenario.log(Status.FAIL, "Failed to open login page");
            throw e;
        }
    }

    @When("the user enters a username as {string}")
    public void the_user_enters_a_username(String username) {
        try {
            loginPage.enterUsername(username);
            Hooks.scenario.log(Status.PASS, "Username entered: " + username);
        } catch (Exception e) {
            Hooks.scenario.log(Status.FAIL, "Failed to enter username: " + username);
            throw e;
        }
    }

    @And("the user enters a password as {string}")
    public void the_user_enters_a_password_as(String password) {
        try {
            loginPage.enterPassword(password);
            Hooks.scenario.log(Status.PASS, "Password entered successfully");
        } catch (Exception e) {
            Hooks.scenario.log(Status.FAIL, "Failed to enter password");
            throw e;
        }
    }

    @And("clicks on the login button")
    public void clicks_on_the_login_button() {
        try {
            loginPage.clickLoginButton();
            Hooks.scenario.log(Status.PASS, "Login button clicked");
        } catch (Exception e) {
            Hooks.scenario.log(Status.FAIL, "Failed to click login button");
            throw e;
        }
    }

    @Then("the user should see a successful login message")
    public void the_user_should_see_a_successful_login_message() {
        try {
            String successMessage = loginPage.getSuccessMessage();
            Assertions.assertTrue(successMessage.contains("You logged into a secure area"),
                    "Le message de succès ne contient pas le texte attendu");

            System.out.println("Message de succès: " + successMessage);
            Hooks.scenario.log(Status.PASS, "Successful login message displayed: " + successMessage);
        } catch (Exception e) {
            Hooks.scenario.log(Status.FAIL, "Failed to verify successful login message: " + e.getMessage());
            throw e;
        }
    }

    @Then("the user should see an error message")
    public void the_user_should_see_an_error_message() {
        try {
            String errorMessage = loginPage.getErrorMessage();
            Assertions.assertTrue(errorMessage.contains("Your username is invalid"),
                    "Le message d'erreur ne contient pas le texte attendu");

            System.out.println("Message d'erreur: " + errorMessage);
            Hooks.scenario.log(Status.PASS, "Error message displayed as expected: " + errorMessage);
        } catch (Exception e) {
            Hooks.scenario.log(Status.FAIL, "Failed to verify error message: " + e.getMessage());
            throw e;
        }
    }

    @Then("the user should see a successful login message containing {string}")
    public void the_user_should_see_a_successful_login_message_containing(String expectedText) {
        try {
            String successMessage = loginPage.getSuccessMessage();
            Assertions.assertTrue(successMessage.contains(expectedText),
                    "Le message de succès ne contient pas le texte attendu: " + expectedText);
            Hooks.scenario.log(Status.PASS, "Successful login message contains: " + expectedText);
        } catch (Exception e) {
            Hooks.scenario.log(Status.FAIL,
                    "Failed to verify message containing '" + expectedText + "': " + e.getMessage());
            throw e;
        }
    }
}