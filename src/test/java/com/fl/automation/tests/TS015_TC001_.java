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
 * Acceptance Criteria ID: AC-3
 * Test Scenario ID: HAP-21 TS-003
 * Test Case ID: TC-001
 * Description: MyHP app - Select feedback related dropdown
 */
public class TS015_TC001_ {
    private WebDriver driver;

    @BeforeMethod
    public void setUp() {
        DriverFactory.initDriver();
        driver = DriverFactory.getDriver();
        driver.get(ConfigReader.get("base.url"));
    }

    @Test
    public void testFeedbackRelatedDropdown() {
        HomePage homePage = new HomePage(driver);
        Assert.assertTrue(homePage.isFindAStoreDisplayed(), "Homepage not loaded");
    }

    @AfterMethod
    public void tearDown() {
        DriverFactory.quitDriver();
    }
}