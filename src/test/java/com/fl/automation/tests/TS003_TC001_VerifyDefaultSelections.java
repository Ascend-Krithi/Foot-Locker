package com.fl.automation.tests;

import com.fl.automation.base.BaseTest;
import com.fl.automation.pages.AllPhonesPage;
import com.fl.automation.pages.DeviceDetailsPage;
import com.fl.automation.pages.HomePage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TS003_TC001_VerifyDefaultSelections extends BaseTest {

    @Test
    public void testVerifyDefaultSelections() {
        HomePage homePage = new HomePage(driver);
        AllPhonesPage allPhonesPage = new AllPhonesPage(driver);
        DeviceDetailsPage deviceDetailsPage = new DeviceDetailsPage(driver);

        homePage.navigateToUrl("https://www.starhub.com");
        homePage.acceptCookies();
        homePage.clickMobilesMenu();
        homePage.clickAllPhones();
        allPhonesPage.clickDevice("Samsung Galaxy A57 5G");

        String colour = deviceDetailsPage.getSelectedColour();
        String storage = deviceDetailsPage.getSelectedStorage();
        String payment = deviceDetailsPage.getSelectedPayment();

        Assert.assertTrue(colour.contains("Black"), "Default colour should be Black");
        Assert.assertTrue(storage.contains("256"), "Default storage should be 256 GB");
        Assert.assertTrue(payment.contains("24"), "Default payment should be 24-month installment");
    }
}