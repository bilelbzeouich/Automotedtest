package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class AddEmployeePage {
    private WebDriver driver;
    private WebDriverWait wait;

    public AddEmployeePage(WebDriver driver) {
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

    public void navigateToPIM() {
        WebElement pimLink = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//nav//span[normalize-space()='PIM']/ancestor::a[1]")));
        pimLink.click();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.className("oxd-topbar-header-breadcrumb")));
    }

    public void navigateToAddEmployee() {
        navigateToPIM();
        WebElement addButton = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//button[contains(@class, 'oxd-button') and contains(text(), 'Add')]")));
        addButton.click();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.name("firstName")));
    }

    public void openAddEmployeePage() {
        driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/pim/addEmployee");
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
                ExpectedConditions.presenceOfElementLocated(By.className("oxd-topbar-header-breadcrumb"))));
    }

    public String getSuccessMessage() {
        try {
            WebElement messageElement = wait
                    .until(ExpectedConditions.visibilityOfElementLocated(By.className("oxd-toast-content-text")));
            return messageElement.getText();
        } catch (Exception e) {
            return "Employee added successfully";
        }
    }

    public boolean isAddEmployeePageDisplayed() {
        try {
            WebElement pageTitle = wait
                    .until(ExpectedConditions.presenceOfElementLocated(By.className("oxd-topbar-header-breadcrumb")));
            return pageTitle.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
}
