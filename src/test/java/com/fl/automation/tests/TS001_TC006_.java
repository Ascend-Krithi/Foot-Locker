package com.fl.automation.tests;

import com.fl.automation.core.BaseTest;
import com.fl.automation.helpers.StoreLocatorHelper;
import com.fl.automation.pages.HomePage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TS001_TC006_ extends BaseTest {

    @Test
    public void testCase_SCRUM17166_TS001_TC006() {
        HomePage homePage = new HomePage(driver);
        StoreLocatorHelper storeLocatorHelper = new StoreLocatorHelper(driver);

        Assert.assertTrue(driver.getTitle().contains("Foot Locker"), "Homepage did not load correctly");

        homePage.clickFindAStore();

        Assert.assertTrue(homePage.isPopupDisplayed(), "Popup did not appear");

        storeLocatorHelper.clickSelectMyStore();

        storeLocatorHelper.enterLocation("Boston, MA");

        storeLocatorHelper.clickSearchForStores();

        Assert.assertTrue(storeLocatorHelper.areStoreResultsDisplayed(), "Search results are not displayed");

        Assert.assertTrue(storeLocatorHelper.isStoreAddressPresent("375 Washington Street, Boston, MA 02108"), "Store is not found in the results");

        storeLocatorHelper.setMyStoreByAddress("375 Washington Street, Boston, MA 02108");

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Assert.assertTrue(storeLocatorHelper.isStoreSetAsPreferred("Boston"), "Confirmation indicator is not displayed or selected store does not appear consistently across the website");
    }
}