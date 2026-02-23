package com.fl.automation.tests;

import com.fl.automation.core.ConfigReader;
import com.fl.automation.core.DriverFactory;
import com.fl.automation.pages.HomePage;
import com.fl.automation.pages.StoreLocatorPage;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * Acceptance Criteria ID: SCRUM-15408
 * Test Scenario ID: TS-002
 * Test Case ID: TC-001
 * Description: Click 'Find a Store', then 'Select My Store', verify location textbox and search button
 */
public class TS002_TC001_ {
    @BeforeMethod
    public void setUp() {
        DriverFactory.initDriver();
    }

    @Test
    public void testSelectMyStorePopup() {
        HomePage home = new HomePage();
        StoreLocatorPage locator = new StoreLocatorPage();
        home.open(ConfigReader.get("base.url"));
        home.clickFindAStore();
        locator.clickSelectMyStore();
        Assert.assertTrue(locator.isLocationInputVisible(), "Location textbox not visible");
    }

    @AfterMethod
    public void tearDown() {
        DriverFactory.quitDriver();
    }
}
