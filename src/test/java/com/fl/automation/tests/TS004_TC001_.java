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
 * Acceptance Criteria ID: 2166
 * Test Scenario ID: SCRUM-15408 TS-004
 * Test Case ID: TC-001
 * Description: Launch website -> Click 'Find a Store' -> Click 'Select My Store' -> Enter 'Boston, MA' -> Click 'Search for Stores' -> Review search results for store address '375 Washington Street, Boston, MA 02108'
 * Expected: Store with exact address visible in results
 */
public class TS004_TC001_ {
    private HomePage homePage;
    private StoreLocatorPage storeLocatorPage;
    private StoreResultsPage storeResultsPage;

    @BeforeMethod
    public void setUp() {
        homePage = new HomePage();
        storeLocatorPage = new StoreLocatorPage();
        storeResultsPage = new StoreResultsPage();
        homePage.launch();
    }

    @Test
    public void testStoreAddressVisible() {
        homePage.clickFindAStore();
        homePage.clickSelectMyStore();
        storeLocatorPage.enterLocation("Boston, MA");
        storeLocatorPage.clickSearchForStores();
        Assert.assertTrue(storeResultsPage.isStoreCardWithAddressDisplayed("375 Washington Street, Boston, MA 02108"), "Store with address not found in results");
    }

    @AfterMethod
    public void tearDown() {
        DriverFactory.quitDriver();
    }
}
