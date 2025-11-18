package stepdefinitions;

import base.TestBase;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import extendreport.ExtentManager;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;

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
        if (scenario.isFailed()) {
            String screenshotPath = TestBase.captureScreenshot(scenario.getName());

            if (!screenshotPath.isEmpty() && Hooks.scenario != null) {
                try {
                    String relativePath = screenshotPath.replace(System.getProperty("user.dir") + "\\", "")
                            .replace("\\", "/");

                    Hooks.scenario.fail("Test Failed",
                            MediaEntityBuilder.createScreenCaptureFromPath(relativePath).build());
                } catch (Exception e) {
                    Hooks.scenario.fail("Test Failed - Screenshot: " + screenshotPath);
                    System.err.println("Erreur lors de l'ajout du screenshot au rapport: " + e.getMessage());
                }
            } else if (Hooks.scenario != null) {
                Hooks.scenario.fail("Test Failed - No screenshot available");
            }
        } else if (Hooks.scenario != null) {
            Hooks.scenario.pass("Test Passed");
        }

        if (extent != null) {
            extent.flush();
        }

        TestBase.closeDriver();
    }
}