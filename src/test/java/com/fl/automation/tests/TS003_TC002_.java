package com.fl.automation.tests;

import com.fl.automation.core.DriverFactory;
import com.fl.automation.pages.HomePage;
import com.fl.automation.pages.StoreLocatorPage;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * Acceptance Criteria ID: 2435
 * Test Scenario ID: SCRUM-15408 TS-003
 * Test Case ID: TC-002
 * Description: Launch homepage, click Find a Store, click Select My Store, enter InvalidLocation123, click Search for Stores, verify error message
 */
public class TS003_TC002_ {
    @BeforeMethod
    public void setUp() {
        DriverFactory.initDriver();
    }
    @AfterMethod
    public void tearDown() {
        DriverFactory.quitDriver();
    }
    @Test
    public void test_TS003_TC002_() {
        HomePage home = new HomePage();
        home.open();
        home.clickFindAStoreHeader();
        StoreLocatorPage locator = new StoreLocatorPage();
        locator.enterSearch("InvalidLocation123");
        Assert.assertTrue(locator.isEmptyResultsMessageDisplayed(), "Error message or no results should be displayed");
    }
}