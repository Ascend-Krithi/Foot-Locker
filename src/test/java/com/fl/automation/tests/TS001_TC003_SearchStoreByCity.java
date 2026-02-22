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
 * Acceptance Criteria ID: 1936
 * Test Scenario ID: SCRUM-15408 TS-001
 * Test Case ID: TC-003
 * Description: Search for stores by city name and verify results
 */
public class TS001_TC003_SearchStoreByCity {
    @BeforeMethod
    public void setUp() {
        DriverFactory.initDriver();
    }

    @Test
    public void testSearchStoreByCity() {
        HomePage homePage = new HomePage();
        homePage.open();
        homePage.clickFindStoreHeader();
        StoreLocatorPage locatorPage = new StoreLocatorPage();
        locatorPage.enterSearchText("New York");
        StoreResultsPage resultsPage = new StoreResultsPage();
        Assert.assertTrue(resultsPage.getStoreResultsCount() > 0, "Should display store results for city");
    }

    @AfterMethod
    public void tearDown() {
        DriverFactory.quitDriver();
    }
}
