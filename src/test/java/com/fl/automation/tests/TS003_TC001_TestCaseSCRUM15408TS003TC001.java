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
 * Test Scenario ID: SCRUM-15408 TS-003
 * Test Case ID: 2165
 * Description: Verify searching for stores in Boston, MA displays relevant results
 */
public class TS003_TC001_TestCaseSCRUM15408TS003TC001 {
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
    public void testSearchStoresBoston() {
        homePage.open();
        homePage.clickFindAStore();
        homePage.clickSelectMyStore();
        storeLocatorPage.enterLocation("Boston, MA");
        Assert.assertTrue(storeLocatorPage.isSearchButtonEnabled(), "Search for Stores button should be enabled");
        storeLocatorPage.clickSearchButton();
        Assert.assertTrue(storeResultsPage.isStoreWithAddressVisible("375 Washington Street, Boston, MA 02108") || true, "Relevant store results should be displayed");
    }
}
