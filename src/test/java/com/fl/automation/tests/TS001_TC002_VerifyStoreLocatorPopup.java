package com.fl.automation.tests;

import com.fl.automation.core.BaseTest;
import com.fl.automation.helpers.StoreLocatorHelper;
import com.fl.automation.pages.HomePage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TS001_TC002_VerifyStoreLocatorPopup extends BaseTest {

    @Test(description = "TC4201 - Click Find a Store, click Select My Store, verify Location textbox and Search button")
    public void testVerifyStoreLocatorPopup() throws InterruptedException {
        HomePage homePage = new HomePage(driver);
        StoreLocatorHelper storeLocatorHelper = new StoreLocatorHelper(driver);

        homePage.acceptCookiesIfPresent();
        Thread.sleep(2000);

        homePage.clickFindStore();
        Thread.sleep(2000);

        Assert.assertTrue(homePage.isStorePopupDisplayed(), "Popup did not appear below Find a Store");

        homePage.clickSelectMyStore();
        Thread.sleep(3000);

        Assert.assertTrue(storeLocatorHelper.isLocationTextboxDisplayed(), "Location textbox is not present in Find a Store popup");

        Assert.assertTrue(storeLocatorHelper.isSearchButtonDisplayed(), "Search for Stores button is not present in Find a Store popup");
    }
}