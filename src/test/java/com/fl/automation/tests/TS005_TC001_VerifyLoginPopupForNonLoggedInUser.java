package com.fl.automation.tests;

import com.fl.automation.base.BaseTest;
import com.fl.automation.pages.AllPhonesPage;
import com.fl.automation.pages.DeviceDetailsPage;
import com.fl.automation.pages.HomePage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TS005_TC001_VerifyLoginPopupForNonLoggedInUser extends BaseTest {

    @Test
    public void testVerifyLoginPopupForNonLoggedInUser() {
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
        Assert.assertTrue(deviceDetailsPage.isLoginPopupMessageDisplayed(), "Login popup message should be displayed");
        Assert.assertTrue(deviceDetailsPage.isLoginWithHubIdButtonVisible(), "Login with Hub ID button should be visible");
        Assert.assertTrue(deviceDetailsPage.isSignUpLinkVisible(), "Sign up link should be visible");
    }
}