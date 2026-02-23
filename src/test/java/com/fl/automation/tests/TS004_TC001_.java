package com.fl.automation.tests;

import com.fl.automation.core.ConfigReader;
import com.fl.automation.core.DriverFactory;
import com.fl.automation.pages.HomePage;
import com.fl.automation.pages.StoreLocatorPage;
import com.fl.automation.pages.StoreResultsPage;
import org.testng.Assert;
import org.testng.annotations.*;

/**
 * Acceptance Criteria ID: 2166
 * Test Scenario ID: SCRUM-15408 TS-004
 * Test Case ID: TC-001
 * Description: Launch website -> Click Find a Store -> Click Select My Store -> Enter 'Boston, MA' -> Click Search for Stores -> Review search results for store address '375 Washington Street, Boston, MA 02108'
 */
public class TS004_TC001_ {
    @BeforeMethod
    public void setUp() {
        DriverFactory.initDriver();
        DriverFactory.getDriver().get(ConfigReader.get("baseUrl"));
    }
    @Test
    public void testBostonStoreAddressDisplayed() {
        HomePage home = new HomePage();
        home.clickFindAStore();
        StoreLocatorPage locator = new StoreLocatorPage();
        locator.clickSelectMyStore();
        StoreResultsPage results = new StoreResultsPage();
        results.enterLocation("Boston, MA");
        results.clickSearch();
        Assert.assertTrue(results.isStoreResultDisplayed("375 Washington Street, Boston, MA 02108"), "Store with exact address should be visible in results");
    }
    @AfterMethod
    public void tearDown() {
        DriverFactory.quitDriver();
    }
}
