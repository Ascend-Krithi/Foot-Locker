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
 * Test Scenario ID: TS-004
 * Test Case ID: TC-001
 * Description: Launch homepage, Click Find a Store, Click Select My Store, Enter 'Boston, MA', Click Search, Verify store address '375 Washington Street, Boston, MA 02108' is visible
 */
public class TS004_TC001_TestCaseSCRUM15408TS004TC001 {
    @BeforeMethod
    public void setUp() {
        DriverFactory.initDriver();
    }

    @AfterMethod
    public void tearDown() {
        DriverFactory.quitDriver();
    }

    @Test
    public void testStoreAddressVisibleForBostonMA() {
        HomePage homePage = new HomePage();
        homePage.open();
        homePage.clickFindAStore();
        StoreLocatorPage locatorPage = new StoreLocatorPage();
        locatorPage.clickSelectMyStore();
        locatorPage.enterLocation("Boston, MA");
        locatorPage.clickSearch();
        StoreResultsPage resultsPage = new StoreResultsPage();
        Assert.assertTrue(resultsPage.isStoreAddressVisible("375 Washington Street, Boston, MA 02108"),
                "Store address should be visible in results");
    }
}
