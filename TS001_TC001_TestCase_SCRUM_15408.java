package tests;

import core.DriverFactory;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.StoreLocatorPage;

/**
 * TC_ID: 2163
 * Description: Launch homepage, click 'Find a Store', verify popup and 'Select My Store' link
 */
public class TS001_TC001_TestCase_SCRUM_15408 {
    @BeforeMethod
    public void setUp() {
        DriverFactory.initDriver();
    }

    @Test
    public void testFindAStorePopup() {
        HomePage home = new HomePage();
        home.openHomePage();
        home.clickFindAStore();
        StoreLocatorPage locator = new StoreLocatorPage();
        Assert.assertTrue(locator.isPopupMessageDisplayed(), "Popup message not displayed");
        Assert.assertTrue(locator.isSelectMyStoreLinkVisible(), "'Select My Store' link not visible");
    }

    @AfterMethod
    public void tearDown() {
        DriverFactory.quitDriver();
    }
}
