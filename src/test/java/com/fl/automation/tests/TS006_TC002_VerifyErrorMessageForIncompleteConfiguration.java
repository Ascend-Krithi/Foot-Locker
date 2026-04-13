package com.fl.automation.tests;

import com.fl.automation.base.BaseTest;
import com.fl.automation.pages.AllPhonesPage;
import com.fl.automation.pages.DeviceDetailsPage;
import com.fl.automation.pages.HomePage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TS006_TC002_VerifyErrorMessageForIncompleteConfiguration extends BaseTest {

    @Test
    public void testVerifyErrorMessageForIncompleteConfiguration() {
        HomePage homePage = new HomePage(driver);
        AllPhonesPage allPhonesPage = new AllPhonesPage(driver);
        DeviceDetailsPage deviceDetailsPage = new DeviceDetailsPage(driver);

        homePage.navigateToUrl("https://www.starhub.com");
        homePage.acceptCookies();
        homePage.clickMobilesMenu();
        homePage.clickAllPhones();
        allPhonesPage.clickDevice("Samsung Galaxy A57 5G");

        deviceDetailsPage.selectColour("Black");

        boolean nextButtonEnabled = deviceDetailsPage.isNextButtonEnabled();
        Assert.assertFalse(nextButtonEnabled, "Next button should be disabled with incomplete configuration");

        deviceDetailsPage.clickNextButton();
        Assert.assertFalse(deviceDetailsPage.isUserNavigatedToNextStep(), "User should not proceed with incomplete configuration");
    }
}