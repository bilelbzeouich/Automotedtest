package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginPage {
    private WebDriver driver;
    private WebDriverWait wait;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void openLoginPage() {
        driver.get("https://opensource-demo.orangehrmlive.com/");
    }

    public void enterUsername(String username) {
        WebElement usernameElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("username")));
        usernameElement.clear();
        usernameElement.sendKeys(username);
    }

    public void enterPassword(String password) {
        WebElement passwordElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("password")));
        passwordElement.clear();
        passwordElement.sendKeys(password);
    }

    public void clickLoginButton() {
        WebElement button = wait
                .until(ExpectedConditions.elementToBeClickable(By.cssSelector("button[type='submit']")));
        button.click();
    }

    public String getSuccessMessage() {
        // Wait for dashboard to appear (indicates successful login)
        WebElement dashboardElement = wait
                .until(ExpectedConditions.presenceOfElementLocated(By.className("oxd-topbar-header-breadcrumb")));
        return dashboardElement.getText();
    }

    public String getErrorMessage() {
        WebElement messageElement = wait
                .until(ExpectedConditions.visibilityOfElementLocated(By.className("oxd-alert-content-text")));
        return messageElement.getText();
    }
}