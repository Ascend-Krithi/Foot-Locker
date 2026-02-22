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
 * Acceptance Criteria ID: 1937
 * Test Scenario ID: SCRUM-15408 TS-001
 * Test Case ID: TC-004
 * Description: Search for stores by ZIP code and verify results
 */
public class TS001_TC004_SearchStoreByZip {
    @BeforeMethod
    public void setUp() {
        DriverFactory.initDriver();
    }

    @Test
    public void testSearchStoreByZip() {
        HomePage homePage = new HomePage();
        homePage.open();
        homePage.clickFindStoreHeader();
        StoreLocatorPage locatorPage = new StoreLocatorPage();
        locatorPage.enterSearchText("10001");
        StoreResultsPage resultsPage = new StoreResultsPage();
        Assert.assertTrue(resultsPage.getStoreResultsCount() > 0, "Should display store results for ZIP code");
    }

    @AfterMethod
    public void tearDown() {
        DriverFactory.quitDriver();
    }
}
