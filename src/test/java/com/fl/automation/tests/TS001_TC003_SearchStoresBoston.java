package com.fl.automation.tests;

import com.fl.automation.core.BaseTest;
import com.fl.automation.helpers.StoreLocatorHelper;
import com.fl.automation.pages.HomePage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TS001_TC003_SearchStoresBoston extends BaseTest {

    @Test(description = "TC4202 - Open Find a Store popup, enter Boston MA, click Search, verify store results")
    public void testSearchStoresBoston() throws InterruptedException {
        HomePage homePage = new HomePage(driver);
        StoreLocatorHelper storeLocatorHelper = new StoreLocatorHelper(driver);

        homePage.acceptCookiesIfPresent();
        Thread.sleep(2000);

        homePage.clickFindStore();
        Thread.sleep(2000);

        homePage.clickSelectMyStore();
        Thread.sleep(3000);

        Assert.assertTrue(storeLocatorHelper.isLocationTextboxDisplayed(), "Find a Store popup window is not displayed");

        storeLocatorHelper.enterLocation("Boston, MA");
        Thread.sleep(1000);

        storeLocatorHelper.clickSearchForStores();
        Thread.sleep(4000);

        Assert.assertTrue(storeLocatorHelper.areStoreResultsDisplayed(), "Search results are not displayed in the popup window");
    }
}