package tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.StoreLocatorPage;
import pages.StoreResultsPage;

public class TC3233_StorePersistence extends BaseTest {
    @Test
    public void testStorePersistence() {
        HomePage home = new HomePage(driver);
        home.clickFindAStore();
        home.clickSelectMyStore();
        StoreLocatorPage locator = new StoreLocatorPage(driver);
        locator.enterLocation("Boston, MA");
        locator.clickSearch();
        StoreResultsPage results = new StoreResultsPage(driver);
        results.clickSetMyStore("375 Washington Street, Boston, MA 02108");
        driver.navigate().refresh();
        Assert.assertTrue(results.isConfirmationIndicatorDisplayed(), "Store should persist after navigation");
    }
}
