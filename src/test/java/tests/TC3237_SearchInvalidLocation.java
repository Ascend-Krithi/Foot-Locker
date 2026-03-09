package tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.StoreLocatorPage;

public class TC3237_SearchInvalidLocation extends BaseTest {
    @Test
    public void testSearchInvalidLocation() {
        HomePage home = new HomePage(driver);
        home.clickFindAStore();
        home.clickSelectMyStore();
        StoreLocatorPage locator = new StoreLocatorPage(driver);
        locator.enterLocation("Xyzabc");
        locator.clickSearch();
        Assert.assertFalse(locator.isResultsDisplayed(), "No results should be shown for invalid location");
    }
}
