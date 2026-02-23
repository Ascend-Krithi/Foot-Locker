package com.fl.automation.tests;

import com.fl.automation.core.DriverFactory;
import com.fl.automation.pages.HomePage;
import com.fl.automation.pages.StoreResultsPage;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * Acceptance Criteria ID: 2553
 * Test Scenario ID: SCRUM-15408 TS-001
 * Test Case ID: TC-001
 * Description: Launch Foot Locker homepage, click Find a Store, verify popup message and Select My Store link
 */
public class TS001_TC001_2553 {
    @BeforeMethod
    public void setUp() {
        DriverFactory.initDriver();
    }
    @AfterMethod
    public void tearDown() {
        DriverFactory.quitDriver();
    }
    @Test
    public void test_TS001_TC001_2553() {
        HomePage home = new HomePage();
        home.open();
        home.clickFindAStoreHeader();
        StoreResultsPage results = new StoreResultsPage();
        Assert.assertTrue(results.isSelectMyStoreLinkPresent(), "Select My Store link should be present");
    }
}