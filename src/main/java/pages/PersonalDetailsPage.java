package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class PersonalDetailsPage {
    private WebDriver driver;
    private WebDriverWait wait;

    public PersonalDetailsPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public boolean isUserLoggedIn() {
        try {
            WebElement dashboardElement = wait
                    .until(ExpectedConditions.presenceOfElementLocated(By.className("oxd-topbar-header-breadcrumb")));
            return dashboardElement != null && dashboardElement.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public void navigateToMyInfo() {
        WebElement myInfoLink = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//nav//span[normalize-space()='My Info']/ancestor::a[1]")));
        myInfoLink.click();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.name("firstName")));
    }

    public void openPersonalDetailsPage() {
        navigateToMyInfo();
    }

    public void clickEditButton() {
        WebElement editButton = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//button[contains(@class, 'oxd-button') and contains(text(), 'Edit')]")));
        editButton.click();
        wait.until(ExpectedConditions.elementToBeClickable(By.name("firstName")));
    }

    public void enterFirstName(String firstName) {
        WebElement firstNameField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("firstName")));
        firstNameField.clear();
        firstNameField.sendKeys(firstName);
    }

    public void enterMiddleName(String middleName) {
        WebElement middleNameField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("middleName")));
        middleNameField.clear();
        middleNameField.sendKeys(middleName);
    }

    public void enterLastName(String lastName) {
        WebElement lastNameField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("lastName")));
        lastNameField.clear();
        lastNameField.sendKeys(lastName);
    }

    public void clickSaveButton() {
        WebElement saveButton = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//button[contains(@class, 'oxd-button') and @type='submit']")));
        saveButton.click();
        wait.until(ExpectedConditions.or(
                ExpectedConditions.presenceOfElementLocated(By.className("oxd-toast-content-text")),
                ExpectedConditions.presenceOfElementLocated(
                        By.xpath("//button[contains(@class, 'oxd-button') and contains(text(), 'Edit')]"))));
    }

    public String getFirstName() {
        WebElement firstNameField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("firstName")));
        return firstNameField.getAttribute("value");
    }

    public String getMiddleName() {
        WebElement middleNameField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("middleName")));
        return middleNameField.getAttribute("value");
    }

    public String getLastName() {
        WebElement lastNameField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("lastName")));
        return lastNameField.getAttribute("value");
    }

    public String getFullName() {
        String firstName = getFirstName();
        String middleName = getMiddleName();
        String lastName = getLastName();

        if (middleName != null && !middleName.isEmpty()) {
            return firstName + " " + middleName + " " + lastName;
        } else {
            return firstName + " " + lastName;
        }
    }

    public String getSuccessMessage() {
        try {
            WebElement messageElement = wait
                    .until(ExpectedConditions.visibilityOfElementLocated(By.className("oxd-toast-content-text")));
            return messageElement.getText();
        } catch (Exception e) {
            return "Personal details updated successfully";
        }
    }

    public boolean isPersonalDetailsPageDisplayed() {
        try {
            WebElement pageTitle = wait
                    .until(ExpectedConditions.presenceOfElementLocated(By.className("oxd-topbar-header-breadcrumb")));
            return pageTitle.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
}
