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
        // Click on user menu dropdown
        WebElement userMenu = wait.until(ExpectedConditions.elementToBeClickable(By.className("oxd-userdropdown-name")));
        userMenu.click();
        
        // Click on logout link
        WebElement logoutButton = wait
                .until(ExpectedConditions.elementToBeClickable(By.linkText("Logout")));
        logoutButton.click();
    }

    public String getLogoutMessage() {
        // Wait for login page to appear (indicates successful logout)
        wait.until(ExpectedConditions.presenceOfElementLocated(By.className("orangehrm-login-branding")));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("orangehrm-login-form")));
        return "Logged out successfully";
    }
}
