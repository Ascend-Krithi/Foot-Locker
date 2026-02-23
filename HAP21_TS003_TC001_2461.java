package com.fl.automation.tests;

import com.fl.automation.core.ConfigReader;
import com.fl.automation.core.DriverFactory;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
Acceptance Criteria ID: AC-3
Test Scenario ID: HAP-21 TS-003
Test Case ID: TC-001 (2461)
Description: MyHP app - Feedback related dropdown selection
Note: This is a mobile app test case and is out of scope for web automation
*/
public class HAP21_TS003_TC001_2461 {
    private WebDriver driver;

    @BeforeMethod
    public void setUp() {
        DriverFactory.initDriver();
        driver = DriverFactory.getDriver();
        driver.get(ConfigReader.get("base.url"));
    }

    @Test(enabled = false)
    public void testMobileAppFeedbackDropdown() {
        // This test is disabled as it is for MyHP mobile app testing
        // Web automation framework does not support mobile app testing
    }

    @AfterMethod
    public void tearDown() {
        DriverFactory.quitDriver();
    }
}