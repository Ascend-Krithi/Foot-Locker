package com.fl.automation.tests;

import com.fl.automation.core.BaseTest;
import com.fl.automation.pages.HomePage;
import com.fl.automation.helpers.StoreLocatorHelper;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TS001_TC001_LaunchHomepageVerifyFindStore extends BaseTest {

    @Test(description = "TC4200 - TS-001 TC-001: Launch homepage and verify Find a Store functionality")
    public void testLaunchHomepageVerifyFindStore() {
        HomePage homePage = new HomePage(driver);
        StoreLocatorHelper storeLocatorHelper = new StoreLocatorHelper(driver);

        homePage.acceptCookiesIfPresent();
        Assert.assertTrue(homePage.isOnFootLockerDomain(), "Should be on Foot Locker domain");

        homePage.clickFindStore();

        homePage.clickSelectMyStore();

        storeLocatorHelper.waitForStoreLocatorToLoad();
        Assert.assertTrue(storeLocatorHelper.isLocationSearchInputDisplayed(), "Location input should be visible");
    }
}
