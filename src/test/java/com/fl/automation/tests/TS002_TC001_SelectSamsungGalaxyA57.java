package com.fl.automation.tests;

import com.fl.automation.base.BaseTest;
import com.fl.automation.pages.AllPhonesPage;
import com.fl.automation.pages.DeviceDetailsPage;
import com.fl.automation.pages.HomePage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TS002_TC001_SelectSamsungGalaxyA57 extends BaseTest {

    @Test
    public void testSelectSamsungGalaxyA57() {
        HomePage homePage = new HomePage(driver);
        AllPhonesPage allPhonesPage = new AllPhonesPage(driver);
        DeviceDetailsPage deviceDetailsPage = new DeviceDetailsPage(driver);

        homePage.navigateToUrl("https://www.starhub.com");
        homePage.acceptCookies();
        homePage.clickMobilesMenu();
        homePage.clickAllPhones();

        Assert.assertTrue(allPhonesPage.isDeviceVisible("Samsung Galaxy A57 5G"), "Samsung Galaxy A57 5G is not visible");
        allPhonesPage.clickDevice("Samsung Galaxy A57 5G");

        Assert.assertTrue(deviceDetailsPage.isDeviceDetailsPageDisplayed(), "Device details page did not load");
    }
}