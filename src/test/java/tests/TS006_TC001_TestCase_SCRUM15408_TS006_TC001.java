/**
 * TC_ID: 2018
 * TC_Name: Test Case - SCRUM-15408 TS-006 TC-001
 * Description: Launch homepage, Open Store Locator, Search 'Boston, MA', Set store, Navigate to another page. Expected: Selected store remains set and visible across pages
 */
package tests;

import core.DriverFactory;
import core.ConfigReader;
import org.testng.Assert;
import org.testng.annotations.*;
import pages.HomePage;
import pages.StoreLocatorPage;
import pages.StoreResultsPage;

public class TS006_TC001_TestCase_SCRUM15408_TS006_TC001 {
    @BeforeMethod
    public void setUp() {
        DriverFactory.initDriver();
        DriverFactory.getDriver().get(ConfigReader.get("base.url"));
    }

    @Test
    public void testStorePersistsAcrossPages() {
        HomePage home = new HomePage();
        StoreLocatorPage locator = new StoreLocatorPage();
        StoreResultsPage results = new StoreResultsPage();
        home.clickFindAStore();
        locator.clickSelectMyStore();
        locator.enterLocation("Boston, MA");
        locator.clickSearch();
        results.setMyStore("375 Washington Street, Boston, MA 02108");
        // Navigate to another page (simulate by going to homepage again)
        DriverFactory.getDriver().get(ConfigReader.get("base.url"));
        Assert.assertTrue(results.isStoreSet("375 Washington Street, Boston, MA 02108"), "Store remains set across pages");
    }

    @AfterMethod
    public void tearDown() {
        DriverFactory.quitDriver();
    }
}
