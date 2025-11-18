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
import java.io.File;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

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

        // Attach screenshot to ExtentReports using file path (normal image, not Base64)
        // Copy screenshot to target/ folder (same as report) and use relative path
        if (scenario.isFailed()) {
            // Error details are already logged in step definitions
            // This will show the final test failure status with screenshot
            String errorMessage = "Test Failed - See step details above for error information";

            if (!screenshotPath.isEmpty()) {
                try {
                    File screenshotFile = new File(screenshotPath);
                    if (screenshotFile.exists()) {
                        // Copy screenshot to target/ folder (same location as report)
                        File targetDir = new File("target");
                        File targetScreenshot = new File(targetDir, screenshotFile.getName());
                        Files.copy(screenshotFile.toPath(), targetScreenshot.toPath(),
                                StandardCopyOption.REPLACE_EXISTING);

                        // Use relative path from report location (just filename since both are in
                        // target/)
                        String relativePath = screenshotFile.getName();
                        Hooks.scenario.fail(errorMessage,
                                MediaEntityBuilder.createScreenCaptureFromPath(relativePath).build());
                    } else {
                        Hooks.scenario.fail(errorMessage + "\nScreenshot file not found: " + screenshotPath);
                    }
                } catch (Exception e) {
                    Hooks.scenario
                            .fail(errorMessage + "\nScreenshot path: " + screenshotPath + "\nError: " + e.getMessage());
                    System.err.println("Erreur lors de l'ajout du screenshot: " + e.getMessage());
                    e.printStackTrace();
                }
            } else {
                Hooks.scenario.fail(errorMessage);
            }
        } else {
            if (!screenshotPath.isEmpty()) {
                try {
                    File screenshotFile = new File(screenshotPath);
                    if (screenshotFile.exists()) {
                        // Copy screenshot to target/ folder (same location as report)
                        File targetDir = new File("target");
                        File targetScreenshot = new File(targetDir, screenshotFile.getName());
                        Files.copy(screenshotFile.toPath(), targetScreenshot.toPath(),
                                StandardCopyOption.REPLACE_EXISTING);

                        // Use relative path from report location (just filename since both are in
                        // target/)
                        String relativePath = screenshotFile.getName();
                        Hooks.scenario.pass("Test Passed",
                                MediaEntityBuilder.createScreenCaptureFromPath(relativePath).build());
                    } else {
                        Hooks.scenario.pass("Test Passed - Screenshot file not found: " + screenshotPath);
                    }
                } catch (Exception e) {
                    Hooks.scenario
                            .pass("Test Passed - Screenshot path: " + screenshotPath + "\nError: " + e.getMessage());
                    System.err.println("Erreur lors de l'ajout du screenshot: " + e.getMessage());
                    e.printStackTrace();
                }
            } else {
                Hooks.scenario.pass("Test Passed");
            }
        }
        extent.flush();
        TestBase.closeDriver();
    }
}