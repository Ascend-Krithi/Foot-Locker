package com.fl.automation.tests;

import com.fl.automation.core.DriverFactory;
import com.fl.automation.pages.HomePage;
import com.fl.automation.pages.StoreLocatorPage;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * Acceptance Criteria ID: 2560
 * Test Scenario ID: SCRUM-15408 TS-008
 * Test Case ID: TC-001
 * Description: Launch homepage, search for InvalidLocation123, verify error message or no results
 */
public class TS008_TC001_2560 {
    @BeforeMethod
    public void setUp() {
        DriverFactory.initDriver();
    }
    @AfterMethod
    public void tearDown() {
        DriverFactory.quitDriver();
    }
    @Test
    public void test_TS008_TC001_2560() {
        HomePage home = new HomePage();
        home.open();
        home.clickFindAStoreHeader();
        StoreLocatorPage locator = new StoreLocatorPage();
        locator.enterSearch("InvalidLocation123");
        Assert.assertTrue(locator.isEmptyResultsMessageDisplayed(), "Error message or no results should be displayed");
    }
}