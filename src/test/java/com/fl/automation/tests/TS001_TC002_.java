package com.fl.automation.tests;

import com.fl.automation.core.BaseTest;
import com.fl.automation.pages.HomePage;
import com.fl.automation.helpers.StoreLocatorHelper;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TS001_TC002_ extends BaseTest {

    @Test
    public void testCase_SCRUM17166_TS001_TC002() {
        HomePage homePage = new HomePage(driver);
        StoreLocatorHelper storeLocatorHelper = new StoreLocatorHelper(driver);

        homePage.navigateToHomePage("https://www.footlocker.com");
        String pageTitle = homePage.getPageTitle();
        Assert.assertTrue(pageTitle.contains("Foot Locker") || pageTitle.contains("Sneakers"),
                "Homepage did not load correctly");

        homePage.clickFindStore();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Assert.assertTrue(homePage.isStorePopupDisplayed() || homePage.isSelectMyStoreLinkVisible(),
                "Popup did not appear below 'Find a Store'");

        homePage.clickSelectMyStore();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Assert.assertTrue(storeLocatorHelper.isStoreLocatorPopupVisible(),
                "Store locator popup window did not open");

        Assert.assertTrue(storeLocatorHelper.isLocationTextboxVisible(),
                "'Location' textbox is not present in the popup");
        Assert.assertTrue(storeLocatorHelper.isSearchButtonVisible(),
                "'Search for Stores' button is not present in the popup");
    }
}