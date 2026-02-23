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
 * Test Scenario ID: TS-008
 * Test Case ID: TC-001
 * Description: Launch homepage, Click Find a Store, Click Select My Store, Enter 'Atlantis', Click Search, Verify 'No stores found' message
 */
public class TS008_TC001_TestCaseSCRUM15408TS008TC001 {
    @BeforeMethod
    public void setUp() {
        DriverFactory.initDriver();
    }

    @AfterMethod
    public void tearDown() {
        DriverFactory.quitDriver();
    }

    @Test
    public void testNoStoresFoundForAtlantis() {
        HomePage homePage = new HomePage();
        homePage.open();
        homePage.clickFindAStore();
        StoreLocatorPage locatorPage = new StoreLocatorPage();
        locatorPage.clickSelectMyStore();
        locatorPage.enterLocation("Atlantis");
        locatorPage.clickSearch();
        StoreResultsPage resultsPage = new StoreResultsPage();
        Assert.assertTrue(resultsPage.isNoStoresFoundMessageDisplayed(),
                "No stores found message should be displayed for Atlantis");
    }
}
