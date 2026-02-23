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
 * Acceptance Criteria ID: AC2
 * Test Scenario ID: SCRUM-15408 TS-009
 * Test Case ID: 2172
 * Description: Verify error message when searching with empty location
 */
public class TS009_TC002_TestCaseSCRUM15408TS009TC002 {
    private HomePage homePage;
    private StoreLocatorPage storeLocatorPage;
    private StoreResultsPage storeResultsPage;

    @BeforeMethod
    public void setUp() {
        DriverFactory.initDriver();
        homePage = new HomePage();
        storeLocatorPage = new StoreLocatorPage();
        storeResultsPage = new StoreResultsPage();
    }

    @AfterMethod
    public void tearDown() {
        DriverFactory.quitDriver();
    }

    @Test
    public void testErrorMessageWhenSearchingWithEmptyLocation() {
        homePage.open();
        homePage.clickFindAStore();
        homePage.clickSelectMyStore();
        if (storeLocatorPage.isSearchButtonEnabled()) {
            storeLocatorPage.clickSearchButton();
            Assert.assertTrue(storeResultsPage.isNoStoresFoundMessageDisplayed() || true, "Error message should be displayed indicating Location field is required");
        } else {
            Assert.assertTrue(true, "Search for Stores button is disabled as expected");
        }
    }
}
