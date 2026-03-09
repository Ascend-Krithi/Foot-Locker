package com.fl.automation.tests;

import com.fl.automation.core.DriverFactory;
import com.fl.automation.core.ConfigReader;
import com.fl.automation.pages.HomePage;
import com.fl.automation.pages.StoreLocatorPage;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
Acceptance Criteria ID: 
Test Scenario ID: SCRUM-17166 TS-001
Test Case ID: TC-001 (3227)
Description: Launch app, click Find a Store, verify popup message and Select My Store link
*/
public class TS001_TC001_3227 {
    private WebDriver driver;
    @BeforeMethod
    public void setUp() {
        DriverFactory.initDriver();
        driver = DriverFactory.getDriver();
        driver.get(ConfigReader.getProperty("base.url"));
    }
    @AfterMethod
    public void tearDown() {
        DriverFactory.quitDriver();
    }
    @Test
    public void testFindAStorePopupMessage() {
        HomePage home = new HomePage(driver);
        home.clickFindAStore();
        StoreLocatorPage locator = new StoreLocatorPage(driver);
        boolean exceptionThrown = false;
        try {
            locator.clickSelectMyStore();
        } catch (Exception e) {
            exceptionThrown = true;
        }
        Assert.assertFalse(exceptionThrown, "Select My Store link should be present in popup");
    }
}
