package stepdefinitions;

import base.TestBase;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import extendreport.ExtentManager;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class Hooks {

    private static ExtentReports extent;
    public static ExtentTest scenario;

    @Before
    public void setUp(Scenario scenario) {
        extent = ExtentManager.getInstance();

        Hooks.scenario = extent.createTest(scenario.getName());

        if (TestBase.getDriver() == null) {
            TestBase.initializeDriver();
        }
    }

    @After
    public void tearDown(Scenario scenario) {
        // Always capture screenshot regardless of pass/fail
        byte[] screenshotBytes = null;
        String screenshotPath = "";

        try {
            WebDriver driver = TestBase.getDriver();
            if (driver != null) {
                TakesScreenshot ts = (TakesScreenshot) driver;
                screenshotBytes = ts.getScreenshotAs(OutputType.BYTES);

                // Save screenshot to file
                screenshotPath = TestBase.captureScreenshot(scenario.getName());

                // Embed screenshot into Cucumber report
                if (screenshotBytes != null && screenshotBytes.length > 0) {
                    scenario.attach(screenshotBytes, "image/png", "Screenshot");
                }
            }
        } catch (Exception e) {
            System.err.println("Erreur lors de la capture du screenshot: " + e.getMessage());
            e.printStackTrace();
        }

        // Attach screenshot to ExtentReports
        if (scenario.isFailed()) {
            if (screenshotBytes != null && screenshotBytes.length > 0) {
                try {
                    // Use Base64 encoding for ExtentReports
                    String base64Screenshot = java.util.Base64.getEncoder().encodeToString(screenshotBytes);
                    Hooks.scenario.fail("Test Failed",
                            MediaEntityBuilder.createScreenCaptureFromBase64String(base64Screenshot).build());
                } catch (Exception e) {
                    // Fallback to file path if Base64 fails
                    if (!screenshotPath.isEmpty()) {
                        try {
                            String relativePath = screenshotPath.replace(System.getProperty("user.dir"), "")
                                    .replace("\\", "/");
                            if (relativePath.startsWith("/")) {
                                relativePath = relativePath.substring(1);
                            }
                            Hooks.scenario.fail("Test Failed",
                                    MediaEntityBuilder.createScreenCaptureFromPath(relativePath).build());
                        } catch (Exception ex) {
                            Hooks.scenario.fail("Test Failed - Screenshot: " + screenshotPath);
                        }
                    } else {
                        Hooks.scenario.fail("Test Failed - No screenshot available");
                    }
                    System.err.println("Erreur lors de l'ajout du screenshot: " + e.getMessage());
                }
            } else {
                Hooks.scenario.fail("Test Failed - No screenshot available");
            }
        } else {
            if (screenshotBytes != null && screenshotBytes.length > 0) {
                try {
                    // Use Base64 encoding for ExtentReports
                    String base64Screenshot = java.util.Base64.getEncoder().encodeToString(screenshotBytes);
                    Hooks.scenario.pass("Test Passed",
                            MediaEntityBuilder.createScreenCaptureFromBase64String(base64Screenshot).build());
                } catch (Exception e) {
                    // Fallback to file path if Base64 fails
                    if (!screenshotPath.isEmpty()) {
                        try {
                            String relativePath = screenshotPath.replace(System.getProperty("user.dir"), "")
                                    .replace("\\", "/");
                            if (relativePath.startsWith("/")) {
                                relativePath = relativePath.substring(1);
                            }
                            Hooks.scenario.pass("Test Passed",
                                    MediaEntityBuilder.createScreenCaptureFromPath(relativePath).build());
                        } catch (Exception ex) {
                            Hooks.scenario.pass("Test Passed - Screenshot: " + screenshotPath);
                        }
                    } else {
                        Hooks.scenario.pass("Test Passed");
                    }
                    System.err.println("Erreur lors de l'ajout du screenshot: " + e.getMessage());
                }
            } else {
                Hooks.scenario.pass("Test Passed");
            }
        }
        extent.flush();
        TestBase.closeDriver();
    }
}