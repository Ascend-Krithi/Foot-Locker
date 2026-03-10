package com.fl.automation.tests;

import com.fl.automation.core.BaseTest;
import com.fl.automation.helpers.StoreLocatorHelper;
import com.fl.automation.pages.HomePage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TS001_TC002_ extends BaseTest {

    @Test(description = "Test Case - SCRUM-17166 TS-001 TC-002")
    public void testStoreLocatorPopupWithLocationAndSearchButton() {
        HomePage homePage = new HomePage(driver);
        StoreLocatorHelper storeLocatorHelper = new StoreLocatorHelper(driver);

        homePage.navigateToHomePage("https://www.footlocker.com");
        Assert.assertTrue(homePage.isHomePageLoaded(), "Homepage did not load successfully");

        homePage.clickFindAStore();
        Assert.assertTrue(homePage.isFindAStorePopupVisible(), "Popup did not appear below 'Find a Store'");

        storeLocatorHelper.clickSelectMyStore();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Assert.assertTrue(storeLocatorHelper.isSearchInputVisible(), "'Location' textbox is not visible in the store locator popup");
    }
}
