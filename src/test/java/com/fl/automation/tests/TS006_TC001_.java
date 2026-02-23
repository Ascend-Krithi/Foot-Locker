package com.fl.automation.tests;

import com.fl.automation.core.ConfigReader;
import com.fl.automation.core.DriverFactory;
import com.fl.automation.pages.HomePage;
import com.fl.automation.pages.StoreLocatorPage;
import com.fl.automation.pages.StoreResultsPage;
import org.testng.Assert;
import org.testng.annotations.*;

/**
 * Acceptance Criteria ID: 2168
 * Test Scenario ID: SCRUM-15408 TS-006
 * Test Case ID: TC-001
 * Description: Launch website -> Click Find a Store -> Click Select My Store -> Enter 'Boston, MA' -> Click Search for Stores -> Locate store '375 Washington Street, Boston, MA 02108' -> Click Set My Store -> Observe confirmation indicator and store name across website
 */
public class TS006_TC001_ {
    @BeforeMethod
    public void setUp() {
        DriverFactory.initDriver();
        DriverFactory.getDriver().get(ConfigReader.get("baseUrl"));
    }
    @Test
    public void testConfirmationIndicatorAfterSetMyStore() {
        HomePage home = new HomePage();
        home.clickFindAStore();
        StoreLocatorPage locator = new StoreLocatorPage();
        locator.clickSelectMyStore();
        StoreResultsPage results = new StoreResultsPage();
        results.enterLocation("Boston, MA");
        results.clickSearch();
        Assert.assertTrue(results.setMyStore("375 Washington Street, Boston, MA 02108"), "Should be able to set Boston store as preferred");
        Assert.assertTrue(results.isStoreNameDisplayedGlobally("375 Washington Street, Boston, MA 02108"), "Store name should be displayed globally");
    }
    @AfterMethod
    public void tearDown() {
        DriverFactory.quitDriver();
    }
}
