package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LogoutPage {
    private WebDriver driver;
    private WebDriverWait wait;

    public LogoutPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void clickLogoutButton() {
        WebElement logoutButton = wait
                .until(ExpectedConditions.elementToBeClickable(By.cssSelector("a[href='/logout']")));
        logoutButton.click();
    }

    public String getLogoutMessage() {
        WebElement messageElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("flash")));
        return messageElement.getText();
    }
}
