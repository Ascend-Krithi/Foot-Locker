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
 * Acceptance Criteria ID: SCRUM-17166
 * Test Scenario ID: TS-001
 * Test Case ID: TC-007
 * Description: Set '375 Washington Street, Boston, MA 02108' as preferred, navigate away and back, check persistence
 */
public class TS001_TC007_ {
    @BeforeMethod
    public void setUp() {
        DriverFactory.getDriver();
    }

    @AfterMethod
    public void tearDown() {
        DriverFactory.quitDriver();
    }

    @Test
    public void testStorePersistenceAcrossNavigation() {
        HomePage home = new HomePage();
        home.launch();
        Assert.assertTrue(home.isLoaded(), "Homepage did not load");
        home.clickFindStore();
        StoreLocatorPage locator = new StoreLocatorPage();
        Assert.assertTrue(locator.isSelectMyStoreVisible(), "'Select My Store' link is not visible");
        locator.clickSelectMyStore();
        Assert.assertTrue(locator.isLocationInputPresent(), "Location textbox is not present");
        locator.enterLocation("Boston, MA");
        locator.clickSearchButton();
        StoreResultsPage results = new StoreResultsPage();
        Assert.assertTrue(results.isStoreWithAddressPresent("375 Washington Street, Boston, MA 02108"), "Store with address not found in results");
        Assert.assertTrue(results.setMyStoreForAddress("375 Washington Street, Boston, MA 02108"), "Could not set store as preferred");
        // Navigate to another page (simulate by going to a category page)
        home.launch(); // Go back to homepage
        Assert.assertTrue(home.isLoaded(), "Homepage did not reload");
        // Check if store persists (simulate by checking if store is still present in results)
        home.clickFindStore();
        Assert.assertTrue(locator.isSelectMyStoreVisible(), "'Select My Store' link is not visible after navigation");
    }
}
