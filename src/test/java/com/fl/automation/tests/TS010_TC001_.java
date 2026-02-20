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
 * Test Scenario ID: TS-010
 * Test Case ID: TC-001
 * Description: Search for stores and verify at least one result card is displayed
 */
public class TS010_TC001_ {
    @BeforeMethod
    public void setUp() {
        DriverFactory.initDriver();
    }

    @AfterMethod
    public void tearDown() {
        DriverFactory.quitDriver();
    }

    @Test
    public void testAtLeastOneStoreResultDisplayed() {
        HomePage homePage = new HomePage();
        homePage.open();
        homePage.clickFindStore();
        homePage.clickSelectMyStore();
        StoreLocatorPage locatorPage = new StoreLocatorPage();
        locatorPage.enterSearch("Los Angeles");
        locatorPage.clickSearchButton();
        StoreResultsPage resultsPage = new StoreResultsPage();
        Assert.assertTrue(resultsPage.isStoreResultsDisplayed(), "At least one store result card should be displayed");
    }
}
