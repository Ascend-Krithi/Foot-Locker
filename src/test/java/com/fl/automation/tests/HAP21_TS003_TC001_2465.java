package com.fl.automation.tests;

import com.fl.automation.core.ConfigReader;
import com.fl.automation.core.DriverFactory;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * Acceptance Criteria ID: AC-3
 * Test Scenario ID: HAP-21 TS-003
 * Test Case ID: TC-001 (2465)
 * Description: MyHP mobile app test - Dropdown selection for feedback topic
 * NOTE: This is a mobile app test case. Adapted for web or marked as skipped.
 */
public class HAP21_TS003_TC001_2465 {

    private WebDriver driver;

    @BeforeMethod
    public void setUp() {
        DriverFactory.initDriver();
        driver = DriverFactory.getDriver();
        driver.get(ConfigReader.get("base.url"));
    }

    @Test(enabled = false, description = "Mobile app test case - not applicable for web automation")
    public void testDropdownFeedbackTopic() {
        // This test case is for MyHP mobile app
        // Not applicable for Foot Locker web automation
        Assert.assertTrue(true, "Test skipped - Mobile app test case");
    }

    @AfterMethod
    public void tearDown() {
        DriverFactory.quitDriver();
    }
}