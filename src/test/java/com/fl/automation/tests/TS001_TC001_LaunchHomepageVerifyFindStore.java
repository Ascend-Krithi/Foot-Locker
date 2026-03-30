package com.fl.automation.tests;

import com.fl.automation.core.BaseTest;
import com.fl.automation.helpers.StoreLocatorHelper;
import com.fl.automation.pages.HomePage;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners(com.fl.automation.listeners.TestListener.class)
public class TS001_TC001_LaunchHomepageVerifyFindStore extends BaseTest {

    @Test(description = "TC_ID: 221 - Launch Foot Locker homepage and verify Find a Store button")
    public void testLaunchHomepageVerifyFindStore() {
        HomePage homePage = new HomePage(driver);
        StoreLocatorHelper storeLocatorHelper = new StoreLocatorHelper(driver);

        homePage.acceptCookiesIfPresent();
        homePage.clickFindStore();
        Assert.assertTrue(homePage.isSelectMyStoreLinkDisplayed(), "Select My Store link should be visible");
    }
}