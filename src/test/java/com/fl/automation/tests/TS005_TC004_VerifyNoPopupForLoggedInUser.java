package com.fl.automation.tests;

import com.fl.automation.base.BaseTest;
import com.fl.automation.pages.AllPhonesPage;
import com.fl.automation.pages.DeviceDetailsPage;
import com.fl.automation.pages.HomePage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TS005_TC004_VerifyNoPopupForLoggedInUser extends BaseTest {

    @Test
    public void testVerifyNoPopupForLoggedInUser() {
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

        boolean popupDisplayed = deviceDetailsPage.isLoginPopupDisplayed();
        boolean navigatedToNextStep = deviceDetailsPage.isUserNavigatedToNextStep();

        Assert.assertTrue(!popupDisplayed || navigatedToNextStep, "User should proceed without popup if logged in");
    }
}