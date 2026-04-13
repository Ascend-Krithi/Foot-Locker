package com.fl.automation.tests;

import com.fl.automation.base.BaseTest;
import com.fl.automation.pages.AllPhonesPage;
import com.fl.automation.pages.DeviceDetailsPage;
import com.fl.automation.pages.HomePage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TS004_TC002_VerifyNextButtonState extends BaseTest {

    @Test
    public void testVerifyNextButtonState() {
        HomePage homePage = new HomePage(driver);
        AllPhonesPage allPhonesPage = new AllPhonesPage(driver);
        DeviceDetailsPage deviceDetailsPage = new DeviceDetailsPage(driver);

        homePage.navigateToUrl("https://www.starhub.com");
        homePage.acceptCookies();
        homePage.clickMobilesMenu();
        homePage.clickAllPhones();
        allPhonesPage.clickDevice("Samsung Galaxy A57 5G");

        boolean initialState = deviceDetailsPage.isNextButtonEnabled();

        deviceDetailsPage.selectColour("Black");
        deviceDetailsPage.selectStorage("256");
        deviceDetailsPage.selectPayment("24");

        boolean finalState = deviceDetailsPage.isNextButtonEnabled();
        Assert.assertTrue(finalState, "Next button should be enabled after selecting all options");
    }
}