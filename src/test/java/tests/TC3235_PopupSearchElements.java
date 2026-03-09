package tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.StoreLocatorPage;

public class TC3235_PopupSearchElements extends BaseTest {
    @Test
    public void testPopupSearchElements() {
        HomePage home = new HomePage(driver);
        home.clickFindAStore();
        home.clickSelectMyStore();
        StoreLocatorPage locator = new StoreLocatorPage(driver);
        Assert.assertTrue(locator.isResultsDisplayed(), "Search elements should be displayed");
    }
}
