package com.fl.automation.tests;

import com.fl.automation.core.BaseTest;
import com.fl.automation.helpers.StoreLocatorHelper;
import com.fl.automation.pages.HomePage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TS001_TC004_ extends BaseTest {

    @Test
    public void testCase_SCRUM17166_TS001_TC004() {
        HomePage homePage = new HomePage(driver);
        StoreLocatorHelper storeLocatorHelper = new StoreLocatorHelper(driver);

        Assert.assertTrue(driver.getTitle().contains("Foot Locker"), "Homepage did not load correctly");

        homePage.clickFindAStore();

        Assert.assertTrue(homePage.isPopupDisplayed(), "Popup did not appear");

        storeLocatorHelper.clickSelectMyStore();

        storeLocatorHelper.enterLocation("Boston, MA");

        storeLocatorHelper.clickSearchForStores();

        Assert.assertTrue(storeLocatorHelper.areStoreResultsDisplayed(), "Search results are not displayed");

        Assert.assertTrue(storeLocatorHelper.isStoreAddressPresent("375 Washington Street, Boston, MA 02108"), "Store with exact address is not visible in the results");
    }
}