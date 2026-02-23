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
 * Acceptance Criteria ID: SCRUM-15408
 * Test Scenario ID: TS-009
 * Test Case ID: TC-002
 * Description: Launch homepage, Click Find a Store, Click Select My Store, Leave Location empty, Click Search if enabled, Verify error message displayed
 */
public class TS009_TC002_TestCaseSCRUM15408TS009TC002 {
    @BeforeMethod
    public void setUp() {
        DriverFactory.initDriver();
    }

    @AfterMethod
    public void tearDown() {
        DriverFactory.quitDriver();
    }

    @Test
    public void testErrorMessageWhenSearchClickedWithEmptyLocation() {
        HomePage homePage = new HomePage();
        homePage.open();
        homePage.clickFindAStore();
        StoreLocatorPage locatorPage = new StoreLocatorPage();
        locatorPage.clickSelectMyStore();
        if (locatorPage.isSearchButtonEnabled()) {
            locatorPage.clickSearch();
            StoreResultsPage resultsPage = new StoreResultsPage();
            Assert.assertTrue(resultsPage.isNoStoresFoundMessageDisplayed(),
                    "Error message should be displayed when searching with empty location");
        } else {
            Assert.assertTrue(true, "Search button is disabled as expected");
        }
    }
}
