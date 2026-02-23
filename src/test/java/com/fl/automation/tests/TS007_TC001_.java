package com.fl.automation.tests;

import com.fl.automation.core.ConfigReader;
import com.fl.automation.core.DriverFactory;
import com.fl.automation.pages.HomePage;
import com.fl.automation.pages.StoreLocatorPage;
import com.fl.automation.pages.StoreResultsPage;
import org.testng.Assert;
import org.testng.annotations.*;

/**
 * Acceptance Criteria ID: 2169
 * Test Scenario ID: SCRUM-15408 TS-007
 * Test Case ID: TC-001
 * Description: Launch website -> Click Find a Store -> Click Select My Store -> Enter 'Boston, MA' -> Click Search for Stores -> Locate store '375 Washington Street, Boston, MA 02108' -> Click Set My Store -> Navigate to another page on the website
 */
public class TS007_TC001_ {
    @BeforeMethod
    public void setUp() {
        DriverFactory.initDriver();
        DriverFactory.getDriver().get(ConfigReader.get("baseUrl"));
    }
    @Test
    public void testPreferredStorePersistsOnNavigation() {
        HomePage home = new HomePage();
        home.clickFindAStore();
        StoreLocatorPage locator = new StoreLocatorPage();
        locator.clickSelectMyStore();
        StoreResultsPage results = new StoreResultsPage();
        results.enterLocation("Boston, MA");
        results.clickSearch();
        Assert.assertTrue(results.setMyStore("375 Washington Street, Boston, MA 02108"), "Should be able to set Boston store as preferred");
        // Navigate to another page (e.g., Men)
        DriverFactory.getDriver().navigate().to(ConfigReader.get("baseUrl") + "/men");
        Assert.assertTrue(results.isStoreNameDisplayedGlobally("375 Washington Street, Boston, MA 02108"), "Preferred store should remain set and displayed on new page");
    }
    @AfterMethod
    public void tearDown() {
        DriverFactory.quitDriver();
    }
}
