/**
 * TC_ID: 2014
 * TC_Name: Test Case - SCRUM-15408 TS-002 TC-002
 * Description: Launch homepage, Open Store Locator, Enter 'Boston, MA', Click Search. Expected: Message displayed indicating no stores found (negative test)
 */
package tests;

import core.DriverFactory;
import core.ConfigReader;
import org.testng.Assert;
import org.testng.annotations.*;
import pages.HomePage;
import pages.StoreLocatorPage;
import pages.StoreResultsPage;

public class TS002_TC002_TestCase_SCRUM15408_TS002_TC002 {
    @BeforeMethod
    public void setUp() {
        DriverFactory.initDriver();
        DriverFactory.getDriver().get(ConfigReader.get("base.url"));
    }

    @Test
    public void testNoResultsMessageDisplayed() {
        HomePage home = new HomePage();
        StoreLocatorPage locator = new StoreLocatorPage();
        StoreResultsPage results = new StoreResultsPage();
        home.clickFindAStore();
        locator.clickSelectMyStore();
        locator.enterLocation("Boston, MA");
        locator.clickSearch();
        Assert.assertTrue(results.isNoResultsMessageDisplayed(), "No stores found message is displayed");
    }

    @AfterMethod
    public void tearDown() {
        DriverFactory.quitDriver();
    }
}
