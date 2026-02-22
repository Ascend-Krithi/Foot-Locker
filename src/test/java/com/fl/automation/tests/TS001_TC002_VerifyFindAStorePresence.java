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
 * Acceptance Criteria ID: 
 * Test Scenario ID: SCRUM-15408 TS-001
 * Test Case ID: TC-002
 * Description: Launch the Foot Locker website in a supported browser and observe the homepage for the presence of the Find a Store link or button in the header or main navigation.
 */
public class TS001_TC002_VerifyFindAStorePresence {
    private WebDriver driver;

    @BeforeMethod
    public void setUp() {
        DriverFactory.initDriver();
        driver = DriverFactory.getDriver();
        driver.get(ConfigReader.get("base.url"));
    }

    @Test
    public void testVerifyFindAStorePresence() {
        HomePage homePage = new HomePage(driver);
        Assert.assertTrue(homePage.isFindAStoreDisplayed(), "Find a Store option is not visible and enabled");
    }

    @AfterMethod
    public void tearDown() {
        DriverFactory.quitDriver();
    }
}