package com.fl.automation.tests;

import com.fl.automation.core.ConfigReader;
import com.fl.automation.core.DriverFactory;
import com.fl.automation.pages.HomePage;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * Acceptance Criteria ID: AC1
 * Test Scenario ID: SCRUM-15408 TS-009
 * Test Case ID: TC-001
 * Description: Verify accessibility on desktop device
 */
public class TS025_TC002_ {
    private WebDriver driver;

    @BeforeMethod
    public void setUp() {
        DriverFactory.initDriver();
        driver = DriverFactory.getDriver();
        driver.get(ConfigReader.get("base.url"));
    }

    @Test
    public void testAccessibilityDesktop() {
        HomePage homePage = new HomePage(driver);
        Assert.assertTrue(homePage.isFindAStoreDisplayed(), "Find a Store not accessible");
    }

    @AfterMethod
    public void tearDown() {
        DriverFactory.quitDriver();
    }
}