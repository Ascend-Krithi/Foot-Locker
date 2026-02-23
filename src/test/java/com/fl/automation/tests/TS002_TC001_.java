package com.fl.automation.tests;

import com.fl.automation.core.DriverFactory;
import com.fl.automation.pages.HomePage;
import com.fl.automation.pages.StoreLocatorPage;
import com.fl.automation.pages.StoreResultsPage;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * Acceptance Criteria ID: 2433
 * Test Scenario ID: SCRUM-15408 TS-002
 * Test Case ID: TC-001
 * Description: Launch homepage, click Find a Store, click Select My Store, verify Location textbox and Search for Stores button
 */
public class TS002_TC001_ {
    @BeforeMethod
    public void setUp() {
        DriverFactory.initDriver();
    }
    @AfterMethod
    public void tearDown() {
        DriverFactory.quitDriver();
    }
    @Test
    public void test_TS002_TC001_() {
        HomePage home = new HomePage();
        home.open();
        home.clickFindAStoreHeader();
        StoreLocatorPage locator = new StoreLocatorPage();
        locator.enterSearch("Boston, MA");
        StoreResultsPage results = new StoreResultsPage();
        Assert.assertTrue(results.getStoreCards().size() > 0, "Store cards should be displayed");
    }
}