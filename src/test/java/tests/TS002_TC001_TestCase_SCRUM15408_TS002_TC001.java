/**
 * TC_ID: 2013
 * TC_Name: Test Case - SCRUM-15408 TS-002 TC-001
 * Description: Launch homepage, Open Store Locator, Enter 'Boston, MA', Click Search. Expected: Search results displayed showing stores in/near Boston
 */
package tests;

import core.DriverFactory;
import core.ConfigReader;
import org.testng.Assert;
import org.testng.annotations.*;
import pages.HomePage;
import pages.StoreLocatorPage;
import pages.StoreResultsPage;

public class TS002_TC001_TestCase_SCRUM15408_TS002_TC001 {
    @BeforeMethod
    public void setUp() {
        DriverFactory.initDriver();
        DriverFactory.getDriver().get(ConfigReader.get("base.url"));
    }

    @Test
    public void testSearchResultsDisplayed() {
        HomePage home = new HomePage();
        StoreLocatorPage locator = new StoreLocatorPage();
        StoreResultsPage results = new StoreResultsPage();
        home.clickFindAStore();
        locator.clickSelectMyStore();
        locator.enterLocation("Boston, MA");
        locator.clickSearch();
        Assert.assertTrue(results.isResultsDisplayed(), "Search results are displayed for Boston, MA");
    }

    @AfterMethod
    public void tearDown() {
        DriverFactory.quitDriver();
    }
}
