package tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.StoreLocatorPage;

public class TC3194_SelectMyStorePopup extends BaseTest {
    @Test
    public void testSelectMyStorePopup() {
        HomePage home = new HomePage(driver);
        home.clickFindAStore();
        home.clickSelectMyStore();
        StoreLocatorPage locator = new StoreLocatorPage(driver);
        Assert.assertTrue(locator.isResultsDisplayed(), "Location textbox and Search button should be displayed");
    }
}
