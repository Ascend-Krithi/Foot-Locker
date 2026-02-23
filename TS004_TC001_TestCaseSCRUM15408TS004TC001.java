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
 * Acceptance Criteria ID: AC3
 * Test Scenario ID: SCRUM-15408 TS-004
 * Test Case ID: 2166
 * Description: Verify store with exact address is visible in search results
 */
public class TS004_TC001_TestCaseSCRUM15408TS004TC001 {
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
    public void testStoreAddressVisibleInResults() {
        homePage.open();
        homePage.clickFindAStore();
        homePage.clickSelectMyStore();
        storeLocatorPage.enterLocation("Boston, MA");
        storeLocatorPage.clickSearchButton();
        Assert.assertTrue(storeResultsPage.isStoreWithAddressVisible("375 Washington Street, Boston, MA 02108"), "Store with exact address should be visible");
    }
}
