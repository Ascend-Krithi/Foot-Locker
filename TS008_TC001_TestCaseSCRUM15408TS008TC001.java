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
 * Test Scenario ID: SCRUM-15408 TS-008
 * Test Case ID: 2170
 * Description: Verify 'No stores found' message for invalid location
 */
public class TS008_TC001_TestCaseSCRUM15408TS008TC001 {
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
    public void testNoStoresFoundForInvalidLocation() {
        homePage.open();
        homePage.clickFindAStore();
        homePage.clickSelectMyStore();
        storeLocatorPage.enterLocation("Atlantis");
        storeLocatorPage.clickSearchButton();
        Assert.assertTrue(storeResultsPage.isNoStoresFoundMessageDisplayed(), "'No stores found' message should be displayed");
    }
}
