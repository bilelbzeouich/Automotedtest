package stepdefinitions;

import base.TestBase;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.And;
import org.junit.jupiter.api.Assertions;
import pages.FormPage;

public class FormStep {

    private FormPage formPage;

    private FormPage getFormPage() {
        if (formPage == null) {
            System.out.println("Création de FormPage avec le driver de TestBase...");
            formPage = new FormPage(TestBase.getDriver());
        }
        return formPage;
    }

    @Given("the user is on the web form page")
    public void the_user_is_on_the_web_form_page() {
        System.out.println("Exécution: the user is on the web form page");
        FormPage page = getFormPage();
        page.openWebFormPage();
    }

    @When("the user enters text {string} in the text field")
    public void the_user_enters_text_in_the_text_field(String text) {
        System.out.println("Exécution: enter text " + text);
        getFormPage().enterText(text);
    }

    @When("the user enters password {string} in the password field")
    public void the_user_enters_password_in_the_password_field(String password) {
        System.out.println("Exécution: enter password");
        getFormPage().enterPassword(password);
    }

    @When("the user enters {string} in the textarea")
    public void the_user_enters_in_the_textarea(String text) {
        System.out.println("Exécution: enter textarea " + text);
        getFormPage().enterTextarea(text);
    }

    @When("the user selects {string} from the dropdown")
    public void the_user_selects_from_the_dropdown(String value) {
        System.out.println("Exécution: select dropdown " + value);
        getFormPage().selectDropdownByValue(value);
    }

    @When("the user selects index {int} from the dropdown")
    public void the_user_selects_index_from_the_dropdown(Integer index) {
        System.out.println("Exécution: select dropdown index " + index);
        getFormPage().selectDropdownByIndex(index);
    }

    @When("the user enters date {string} in the date picker")
    public void the_user_enters_date_in_the_date_picker(String date) {
        System.out.println("Exécution: enter date " + date);
        getFormPage().enterDate(date);
    }

    @When("the user checks the checkbox")
    public void the_user_checks_the_checkbox() {
        System.out.println("Exécution: check checkbox");
        getFormPage().clickCheckbox();
    }

    @When("the user clicks the radio button")
    public void the_user_clicks_the_radio_button() {
        System.out.println("Exécution: click radio button");
    }

    @When("the user clicks the submit button")
    public void the_user_clicks_the_submit_button() {
        System.out.println("Exécution: click submit button");
        getFormPage().clickSubmitButton();
    }

    @Then("the user should see a success message containing {string}")
    public void the_user_should_see_a_success_message_containing(String expectedMessage) {
        System.out.println("Exécution: verify success message contains " + expectedMessage);
        String actualMessage = getFormPage().getSuccessMessage();
        Assertions.assertTrue(actualMessage.contains(expectedMessage),
                "Le message de succès ne contient pas le texte attendu. Message actuel: " + actualMessage);
        System.out.println("Message de succès vérifié: " + actualMessage);
    }

    @Then("the form should be submitted successfully")
    public void the_form_should_be_submitted_successfully() {
        System.out.println("Exécution: verify form submitted successfully");
        String successMessage = getFormPage().getSuccessMessage();
        Assertions.assertTrue(successMessage.contains("Form submitted"),
                "Le formulaire n'a pas été soumis avec succès. Message: " + successMessage);
        System.out.println("Formulaire soumis avec succès: " + successMessage);
    }
}