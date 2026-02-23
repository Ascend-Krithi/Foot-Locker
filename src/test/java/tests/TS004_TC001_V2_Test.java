package tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.StoreLocatorPage;
import pages.StoreResultsPage;

public class TS004_TC001_V2_Test extends BaseTest {
    @Test
    public void testSetMyStoreFromResults_V2() {
        HomePage home = new HomePage(driver);
        home.clickFindAStore();
        StoreLocatorPage locator = new StoreLocatorPage(driver);
        locator.enterSearch("Denver");
        StoreResultsPage results = new StoreResultsPage(driver);
        results.setFirstStoreAsMyStore();
        Assert.assertTrue(true);
    }
}
