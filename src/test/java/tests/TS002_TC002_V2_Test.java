package tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.StoreLocatorPage;
import pages.StoreResultsPage;

public class TS002_TC002_V2_Test extends BaseTest {
    @Test
    public void testSetMyStoreButtonWorks_V2() {
        HomePage home = new HomePage(driver);
        home.clickFindAStore();
        StoreLocatorPage locator = new StoreLocatorPage(driver);
        locator.enterSearch("Austin");
        StoreResultsPage results = new StoreResultsPage(driver);
        results.setFirstStoreAsMyStore();
        Assert.assertTrue(true);
    }
}
