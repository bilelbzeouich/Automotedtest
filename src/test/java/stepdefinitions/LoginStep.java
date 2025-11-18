package stepdefinitions;

import base.TestBase;
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
    public void the_user_is_on_the_login_page() {
        loginPage.openLoginPage();
    }

    @When("the user enters a username as {string}")
    public void the_user_enters_a_username(String username) {
        loginPage.enterUsername(username);
    }

    @And("the user enters a password as {string}")
    public void the_user_enters_a_password_as(String password) {
        loginPage.enterPassword(password);
    }

    @And("clicks on the login button")
    public void clicks_on_the_login_button() {
        loginPage.clickLoginButton();
    }

    @Then("the user should see a successful login message")
    public void the_user_should_see_a_successful_login_message() {
        String successMessage = loginPage.getSuccessMessage();
        Assertions.assertTrue(successMessage.contains("You logged into a secure area"),
                "Le message de succès ne contient pas le texte attendu");

        System.out.println("Message de succès: " + successMessage);
    }

    @Then("the user should see an error message")
    public void the_user_should_see_an_error_message() {
        String errorMessage = loginPage.getErrorMessage();
        Assertions.assertTrue(errorMessage.contains("Your username is invalid"),
                "Le message d'erreur ne contient pas le texte attendu");

        System.out.println("Message d'erreur: " + errorMessage);
    }
}