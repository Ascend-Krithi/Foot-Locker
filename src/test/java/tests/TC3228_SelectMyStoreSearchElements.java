package tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.StoreLocatorPage;

public class TC3228_SelectMyStoreSearchElements extends BaseTest {
    @Test
    public void testSelectMyStoreSearchElements() {
        HomePage home = new HomePage(driver);
        home.clickFindAStore();
        home.clickSelectMyStore();
        StoreLocatorPage locator = new StoreLocatorPage(driver);
        Assert.assertTrue(locator.isResultsDisplayed(), "Search elements should be displayed");
    }
}
