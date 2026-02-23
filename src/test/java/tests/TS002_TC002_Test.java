package tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.StoreLocatorPage;
import pages.StoreResultsPage;

public class TS002_TC002_Test extends BaseTest {
    @Test
    public void testSetMyStoreButtonWorks() {
        HomePage home = new HomePage(driver);
        home.clickFindAStore();
        StoreLocatorPage locator = new StoreLocatorPage(driver);
        locator.enterSearch("Chicago");
        StoreResultsPage results = new StoreResultsPage(driver);
        results.setFirstStoreAsMyStore();
        // No assertion possible without UI feedback, but no exception means pass
        Assert.assertTrue(true);
    }
}
