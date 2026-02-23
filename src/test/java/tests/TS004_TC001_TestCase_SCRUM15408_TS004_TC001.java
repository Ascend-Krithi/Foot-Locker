/**
 * TC_ID: 2016
 * TC_Name: Test Case - SCRUM-15408 TS-004 TC-001
 * Description: Launch homepage, Open Store Locator, Search 'Boston, MA', Click 'Set My Store' for '375 Washington Street, Boston, MA 02108'. Expected: Selected store is saved as preferred store
 */
package tests;

import core.DriverFactory;
import core.ConfigReader;
import org.testng.Assert;
import org.testng.annotations.*;
import pages.HomePage;
import pages.StoreLocatorPage;
import pages.StoreResultsPage;

public class TS004_TC001_TestCase_SCRUM15408_TS004_TC001 {
    @BeforeMethod
    public void setUp() {
        DriverFactory.initDriver();
        DriverFactory.getDriver().get(ConfigReader.get("base.url"));
    }

    @Test
    public void testSetMyStore() {
        HomePage home = new HomePage();
        StoreLocatorPage locator = new StoreLocatorPage();
        StoreResultsPage results = new StoreResultsPage();
        home.clickFindAStore();
        locator.clickSelectMyStore();
        locator.enterLocation("Boston, MA");
        locator.clickSearch();
        results.setMyStore("375 Washington Street, Boston, MA 02108");
        Assert.assertTrue(results.isStoreSet("375 Washington Street, Boston, MA 02108"), "Store is set as preferred");
    }

    @AfterMethod
    public void tearDown() {
        DriverFactory.quitDriver();
    }
}
