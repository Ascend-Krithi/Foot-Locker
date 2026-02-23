package tests;

import core.DriverFactory;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.StoreLocatorPage;

/**
 * TC_ID: 2170
 * Description: Search for 'Atlantis', verify 'No stores found' message is displayed
 */
public class TS008_TC001_TestCase_SCRUM_15408 {
    @BeforeMethod
    public void setUp() {
        DriverFactory.initDriver();
    }

    @Test
    public void testNoStoresFoundForAtlantis() {
        HomePage home = new HomePage();
        home.openHomePage();
        home.clickFindAStore();
        StoreLocatorPage locator = new StoreLocatorPage();
        locator.clickSelectMyStore();
        locator.enterLocation("Atlantis");
        locator.clickSearchButton();
        Assert.assertTrue(locator.isEmptyResultsMessageDisplayed(), "No stores found message not displayed");
    }

    @AfterMethod
    public void tearDown() {
        DriverFactory.quitDriver();
    }
}
