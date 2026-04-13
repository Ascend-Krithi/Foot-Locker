package com.fl.automation.tests;

import com.fl.automation.base.BaseTest;
import com.fl.automation.pages.AllPhonesPage;
import com.fl.automation.pages.DeviceDetailsPage;
import com.fl.automation.pages.HomePage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TS005_TC002_ClickLoginWithHubId extends BaseTest {

    @Test
    public void testClickLoginWithHubId() {
        HomePage homePage = new HomePage(driver);
        AllPhonesPage allPhonesPage = new AllPhonesPage(driver);
        DeviceDetailsPage deviceDetailsPage = new DeviceDetailsPage(driver);

        homePage.navigateToUrl("https://www.starhub.com");
        homePage.acceptCookies();
        homePage.clickMobilesMenu();
        homePage.clickAllPhones();
        allPhonesPage.clickDevice("Samsung Galaxy A57 5G");

        deviceDetailsPage.selectColour("Black");
        deviceDetailsPage.selectStorage("256");
        deviceDetailsPage.selectPayment("24");
        deviceDetailsPage.clickNextButton();

        Assert.assertTrue(deviceDetailsPage.isLoginPopupDisplayed(), "Login popup should be displayed");
        deviceDetailsPage.clickLoginWithHubId();

        Assert.assertTrue(driver.getCurrentUrl().contains("login") || driver.getCurrentUrl().contains("auth"), "User should be navigated to login page");
    }
}