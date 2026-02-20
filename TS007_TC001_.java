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
 * Test Scenario ID: TS-007
 * Test Case ID: TC-001
 * Description: Search with invalid ZIP and verify empty results message
 */
public class TS007_TC001_ {
    @BeforeMethod
    public void setUp() {
        DriverFactory.initDriver();
    }

    @AfterMethod
    public void tearDown() {
        DriverFactory.quitDriver();
    }

    @Test
    public void testEmptyResultsMessage() {
        HomePage homePage = new HomePage();
        homePage.open();
        homePage.clickFindStore();
        homePage.clickSelectMyStore();
        StoreLocatorPage locatorPage = new StoreLocatorPage();
        locatorPage.enterSearch("99999");
        locatorPage.clickSearchButton();
        StoreResultsPage resultsPage = new StoreResultsPage();
        Assert.assertTrue(resultsPage.isEmptyResultsMessageDisplayed(), "Empty results message should be displayed for invalid ZIP");
    }
}
