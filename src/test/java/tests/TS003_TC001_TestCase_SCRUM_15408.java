package tests;

import core.DriverFactory;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.StoreLocatorPage;
import pages.StoreResultsPage;

/**
 * TC_ID: 2165
 * Description: Enter 'Boston, MA' and verify relevant store results are displayed
 */
public class TS003_TC001_TestCase_SCRUM_15408 {
    @BeforeMethod
    public void setUp() {
        DriverFactory.initDriver();
    }

    @Test
    public void testSearchBostonStores() {
        HomePage home = new HomePage();
        home.openHomePage();
        home.clickFindAStore();
        StoreLocatorPage locator = new StoreLocatorPage();
        locator.clickSelectMyStore();
        locator.enterLocation("Boston, MA");
        locator.clickSearchButton();
        StoreResultsPage results = new StoreResultsPage();
        Assert.assertTrue(results.isStoreResultDisplayed(), "No store results displayed for Boston, MA");
    }

    @AfterMethod
    public void tearDown() {
        DriverFactory.quitDriver();
    }
}
