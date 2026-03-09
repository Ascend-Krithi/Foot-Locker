package tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.StoreLocatorPage;

public class TC3238_EmptyLocationValidation extends BaseTest {
    @Test
    public void testEmptyLocationValidation() {
        HomePage home = new HomePage(driver);
        home.clickFindAStore();
        home.clickSelectMyStore();
        StoreLocatorPage locator = new StoreLocatorPage(driver);
        locator.enterLocation("");
        locator.clickSearch();
        Assert.assertTrue(locator.isValidationMessageDisplayed(), "Validation message should be displayed for empty location");
    }
}
