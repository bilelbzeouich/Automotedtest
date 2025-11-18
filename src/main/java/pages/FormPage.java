package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class FormPage {
    private WebDriver driver;
    private WebDriverWait wait;

    public FormPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void openWebFormPage() {
        driver.get("https://www.selenium.dev/selenium/web/web-form.html");
    }

    public void enterText(String text) {
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("my-text-id")));
        element.clear();
        element.sendKeys(text);
    }

    public void enterPassword(String password) {
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("my-password")));
        element.clear();
        element.sendKeys(password);
    }

    public void enterTextarea(String text) {
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("my-textarea")));
        element.clear();
        element.sendKeys(text);
    }

    public void selectDropdownByValue(String value) {
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("my-select")));
        Select dropdown = new Select(element);
        dropdown.selectByValue(value);
    }

    public void selectDropdownByIndex(int index) {
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("my-select")));
        Select dropdown = new Select(element);
        dropdown.selectByIndex(index);
    }

    public void enterDate(String date) {
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("my-date")));
        element.clear();
        element.sendKeys(date);
    }

    public void clickCheckbox() {
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(By.id("my-check-1")));
        if (!element.isSelected()) {
            element.click();
        }
    }

    public void clickSubmitButton() {
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("button[type='submit']")));
        element.click();
    }

    public String getSuccessMessage() {
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("message")));
        return element.getText();
    }
}