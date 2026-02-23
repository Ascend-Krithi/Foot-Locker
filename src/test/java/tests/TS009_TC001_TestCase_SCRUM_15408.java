package tests;

import core.DriverFactory;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.StoreLocatorPage;

/**
 * TC_ID: 2171
 * Description: Leave Location textbox empty, verify 'Search for Stores' button is disabled
 */
public class TS009_TC001_TestCase_SCRUM_15408 {
    @BeforeMethod
    public void setUp() {
        DriverFactory.initDriver();
    }

    @Test
    public void testSearchButtonDisabledWhenLocationEmpty() {
        HomePage home = new HomePage();
        home.openHomePage();
        home.clickFindAStore();
        StoreLocatorPage locator = new StoreLocatorPage();
        locator.clickSelectMyStore();
        // Do not enter location
        Assert.assertFalse(locator.isSearchButtonEnabled(), "Search for Stores button should be disabled when location is empty");
    }

    @AfterMethod
    public void tearDown() {
        DriverFactory.quitDriver();
    }
}
