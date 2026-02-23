package tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.StoreLocatorPage;
import pages.StoreResultsPage;

public class TS009_TC002_Test extends BaseTest {
    @Test
    public void testFindStoreHeaderNavigatesToLocator_SanJose() {
        HomePage home = new HomePage(driver);
        home.clickFindAStore();
        StoreLocatorPage locator = new StoreLocatorPage(driver);
        locator.enterSearch("San Jose");
        StoreResultsPage results = new StoreResultsPage(driver);
        Assert.assertTrue(results.getStoreCards().size() > 0, "Store results should be displayed");
    }
}
