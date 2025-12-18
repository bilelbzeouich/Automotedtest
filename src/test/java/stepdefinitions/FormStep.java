package stepdefinitions;

import base.TestBase;
import com.aventstack.extentreports.Status;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import org.junit.jupiter.api.Assertions;
import pages.FormPage;

public class FormStep {

    private final FormPage formPage;

    public FormStep() {
        this.formPage = new FormPage(TestBase.getDriver());
    }

    @Given("the user is on the web form page")
    public void the_user_is_on_the_web_form_page() {
        try {
            formPage.openWebFormPage();
            Hooks.scenario.log(Status.PASS, "The user is on the web form page");
        } catch (Exception e) {
            String errorDetails = "Failed to open web form page\n" +
                    "Error: " + e.getMessage() + "\n" +
                    "Cause: " + (e.getCause() != null ? e.getCause().getMessage() : "N/A") + "\n" +
                    "Stack Trace: " + getStackTrace(e);
            Hooks.scenario.log(Status.FAIL, errorDetails);
            throw e;
        }
    }

    @When("the user enters text {string} in the text field")
    public void the_user_enters_text_in_the_text_field(String text) {
        try {
            formPage.enterText(text);
            Hooks.scenario.log(Status.PASS, "Text entered: " + text);
        } catch (Exception e) {
            String errorDetails = "Failed to enter text: " + text + "\n" +
                    "Error: " + e.getMessage() + "\n" +
                    "Cause: " + (e.getCause() != null ? e.getCause().getMessage() : "N/A") + "\n" +
                    "Stack Trace: " + getStackTrace(e);
            Hooks.scenario.log(Status.FAIL, errorDetails);
            throw e;
        }
    }

    @When("the user enters password {string} in the password field")
    public void the_user_enters_password_in_the_password_field(String password) {
        try {
            formPage.enterPassword(password);
            Hooks.scenario.log(Status.PASS, "Password entered successfully");
        } catch (Exception e) {
            String errorDetails = "Failed to enter password\n" +
                    "Error: " + e.getMessage() + "\n" +
                    "Cause: " + (e.getCause() != null ? e.getCause().getMessage() : "N/A") + "\n" +
                    "Stack Trace: " + getStackTrace(e);
            Hooks.scenario.log(Status.FAIL, errorDetails);
            throw e;
        }
    }

    @When("the user enters {string} in the textarea")
    public void the_user_enters_in_the_textarea(String text) {
        try {
            formPage.enterTextarea(text);
            Hooks.scenario.log(Status.PASS, "Textarea entered: " + text);
        } catch (Exception e) {
            String errorDetails = "Failed to enter textarea: " + text + "\n" +
                    "Error: " + e.getMessage() + "\n" +
                    "Cause: " + (e.getCause() != null ? e.getCause().getMessage() : "N/A") + "\n" +
                    "Stack Trace: " + getStackTrace(e);
            Hooks.scenario.log(Status.FAIL, errorDetails);
            throw e;
        }
    }

    @When("the user selects {string} from the dropdown")
    public void the_user_selects_from_the_dropdown(String value) {
        try {
            formPage.selectDropdownByValue(value);
            Hooks.scenario.log(Status.PASS, "Dropdown selected: " + value);
        } catch (Exception e) {
            String errorDetails = "Failed to select dropdown: " + value + "\n" +
                    "Error: " + e.getMessage() + "\n" +
                    "Cause: " + (e.getCause() != null ? e.getCause().getMessage() : "N/A") + "\n" +
                    "Stack Trace: " + getStackTrace(e);
            Hooks.scenario.log(Status.FAIL, errorDetails);
            throw e;
        }
    }

    @When("the user selects index {int} from the dropdown")
    public void the_user_selects_index_from_the_dropdown(Integer index) {
        try {
            formPage.selectDropdownByIndex(index);
            Hooks.scenario.log(Status.PASS, "Dropdown selected by index: " + index);
        } catch (Exception e) {
            String errorDetails = "Failed to select dropdown by index: " + index + "\n" +
                    "Error: " + e.getMessage() + "\n" +
                    "Cause: " + (e.getCause() != null ? e.getCause().getMessage() : "N/A") + "\n" +
                    "Stack Trace: " + getStackTrace(e);
            Hooks.scenario.log(Status.FAIL, errorDetails);
            throw e;
        }
    }

    @When("the user enters date {string} in the date picker")
    public void the_user_enters_date_in_the_date_picker(String date) {
        try {
            formPage.enterDate(date);
            Hooks.scenario.log(Status.PASS, "Date entered: " + date);
        } catch (Exception e) {
            String errorDetails = "Failed to enter date: " + date + "\n" +
                    "Error: " + e.getMessage() + "\n" +
                    "Cause: " + (e.getCause() != null ? e.getCause().getMessage() : "N/A") + "\n" +
                    "Stack Trace: " + getStackTrace(e);
            Hooks.scenario.log(Status.FAIL, errorDetails);
            throw e;
        }
    }

    @When("the user checks the checkbox")
    public void the_user_checks_the_checkbox() {
        try {
            formPage.clickCheckbox();
            Hooks.scenario.log(Status.PASS, "Checkbox checked");
        } catch (Exception e) {
            String errorDetails = "Failed to check checkbox\n" +
                    "Error: " + e.getMessage() + "\n" +
                    "Cause: " + (e.getCause() != null ? e.getCause().getMessage() : "N/A") + "\n" +
                    "Stack Trace: " + getStackTrace(e);
            Hooks.scenario.log(Status.FAIL, errorDetails);
            throw e;
        }
    }

    @When("the user clicks the radio button")
    public void the_user_clicks_the_radio_button() {
        try {
            Hooks.scenario.log(Status.PASS, "Radio button click (if implemented)");
        } catch (Exception e) {
            String errorDetails = "Failed to click radio button\n" +
                    "Error: " + e.getMessage() + "\n" +
                    "Cause: " + (e.getCause() != null ? e.getCause().getMessage() : "N/A") + "\n" +
                    "Stack Trace: " + getStackTrace(e);
            Hooks.scenario.log(Status.FAIL, errorDetails);
            throw e;
        }
    }

    @When("the user clicks the submit button")
    public void the_user_clicks_the_submit_button() {
        try {
            formPage.clickSubmitButton();
            Hooks.scenario.log(Status.PASS, "Submit button clicked");
        } catch (Exception e) {
            String errorDetails = "Failed to click submit button\n" +
                    "Error: " + e.getMessage() + "\n" +
                    "Cause: " + (e.getCause() != null ? e.getCause().getMessage() : "N/A") + "\n" +
                    "Stack Trace: " + getStackTrace(e);
            Hooks.scenario.log(Status.FAIL, errorDetails);
            throw e;
        }
    }

    @Then("the user should see a success message containing {string}")
    public void the_user_should_see_a_success_message_containing(String expectedMessage) {
        try {
            String actualMessage = formPage.getSuccessMessage();
            Assertions.assertTrue(actualMessage.contains(expectedMessage),
                    "Le message de succès ne contient pas le texte attendu. Message actuel: " + actualMessage);
            Hooks.scenario.log(Status.PASS, "Success message verified: " + actualMessage);
        } catch (AssertionError e) {
            String errorDetails = "Assertion Failed: " + e.getMessage() + "\n" +
                    "Expected: Message should contain '" + expectedMessage + "'\n" +
                    "Stack Trace: " + getStackTrace(e);
            Hooks.scenario.log(Status.FAIL, errorDetails);
            throw e;
        } catch (Exception e) {
            String errorDetails = "Failed to verify success message containing '" + expectedMessage + "'\n" +
                    "Error: " + e.getMessage() + "\n" +
                    "Cause: " + (e.getCause() != null ? e.getCause().getMessage() : "N/A") + "\n" +
                    "Stack Trace: " + getStackTrace(e);
            Hooks.scenario.log(Status.FAIL, errorDetails);
            throw e;
        }
    }

    @Then("the form should be submitted successfully")
    public void the_form_should_be_submitted_successfully() {
        try {
            String successMessage = formPage.getSuccessMessage();
            Assertions.assertTrue(successMessage.contains("Form submitted"),
                    "Le formulaire n'a pas été soumis avec succès. Message: " + successMessage);
            Hooks.scenario.log(Status.PASS, "Form submitted successfully: " + successMessage);
        } catch (AssertionError e) {
            String errorDetails = "Assertion Failed: " + e.getMessage() + "\n" +
                    "Stack Trace: " + getStackTrace(e);
            Hooks.scenario.log(Status.FAIL, errorDetails);
            throw e;
        } catch (Exception e) {
            String errorDetails = "Failed to verify form submission\n" +
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
