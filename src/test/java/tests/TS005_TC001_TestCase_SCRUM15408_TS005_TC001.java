/**
 * TC_ID: 2017
 * TC_Name: Test Case - SCRUM-15408 TS-005 TC-001
 * Description: Launch homepage, Open Store Locator, Search 'Boston, MA', Set store, Verify confirmation indicator. Expected: Confirmation indicator displayed and store appears consistently
 */
package tests;

import core.DriverFactory;
import core.ConfigReader;
import org.testng.Assert;
import org.testng.annotations.*;
import pages.HomePage;
import pages.StoreLocatorPage;
import pages.StoreResultsPage;

public class TS005_TC001_TestCase_SCRUM15408_TS005_TC001 {
    @BeforeMethod
    public void setUp() {
        DriverFactory.initDriver();
        DriverFactory.getDriver().get(ConfigReader.get("base.url"));
    }

    @Test
    public void testConfirmationIndicator() {
        HomePage home = new HomePage();
        StoreLocatorPage locator = new StoreLocatorPage();
        StoreResultsPage results = new StoreResultsPage();
        home.clickFindAStore();
        locator.clickSelectMyStore();
        locator.enterLocation("Boston, MA");
        locator.clickSearch();
        results.setMyStore("375 Washington Street, Boston, MA 02108");
        Assert.assertTrue(results.isStoreSet("375 Washington Street, Boston, MA 02108"), "Confirmation indicator is displayed");
    }

    @AfterMethod
    public void tearDown() {
        DriverFactory.quitDriver();
    }
}
