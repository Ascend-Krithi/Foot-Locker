package com.fl.automation.tests;

import com.fl.automation.core.BaseTest;
import com.fl.automation.pages.HomePage;
import com.fl.automation.helpers.StoreLocatorHelper;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TS001_TC003_ extends BaseTest {

    @Test
    public void testSearchForStoresInBoston() {
        HomePage homePage = new HomePage(driver);
        StoreLocatorHelper storeLocatorHelper = new StoreLocatorHelper(driver);

        Assert.assertTrue(driver.getTitle().contains("Foot Locker") || driver.getCurrentUrl().contains("footlocker.com"),
            "Step 1: Homepage should load");

        homePage.clickFindStore();

        Assert.assertTrue(homePage.isStorePopupDisplayed(),
            "Step 2: Popup should appear");

        homePage.clickSelectMyStore();

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        storeLocatorHelper.enterLocation("Boston, MA");

        storeLocatorHelper.clickSearchButton();

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        Assert.assertTrue(storeLocatorHelper.areSearchResultsDisplayed(),
            "Step 5: Search results should be displayed with relevant stores in or near Boston");
    }
}
