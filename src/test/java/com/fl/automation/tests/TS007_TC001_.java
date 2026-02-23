package com.fl.automation.tests;

import com.fl.automation.core.ConfigReader;
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
 * Description: Set preferred store, navigate to another page, verify preferred store remains set
 */
public class TS007_TC001_ {
    @BeforeMethod
    public void setUp() {
        DriverFactory.initDriver();
    }

    @Test
    public void testPreferredStorePersistence() {
        HomePage home = new HomePage();
        StoreLocatorPage locator = new StoreLocatorPage();
        StoreResultsPage results = new StoreResultsPage();
        home.open(ConfigReader.get("base.url"));
        home.clickFindAStore();
        locator.clickSelectMyStore();
        locator.enterLocation("Boston, MA");
        locator.clickSearchForStores();
        results.setMyStoreByAddress("375 Washington Street, Boston, MA 02108");
        // Navigate to another page (simulate by going to homepage)
        home.open(ConfigReader.get("base.url"));
        Assert.assertTrue(results.isPreferredStoreSet("375 Washington Street, Boston, MA 02108"), "Preferred store not persistent");
    }

    @AfterMethod
    public void tearDown() {
        DriverFactory.quitDriver();
    }
}
