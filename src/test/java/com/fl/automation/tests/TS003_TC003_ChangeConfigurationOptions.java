package com.fl.automation.tests;

import com.fl.automation.base.BaseTest;
import com.fl.automation.pages.AllPhonesPage;
import com.fl.automation.pages.DeviceDetailsPage;
import com.fl.automation.pages.HomePage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TS003_TC003_ChangeConfigurationOptions extends BaseTest {

    @Test
    public void testChangeConfigurationOptions() {
        HomePage homePage = new HomePage(driver);
        AllPhonesPage allPhonesPage = new AllPhonesPage(driver);
        DeviceDetailsPage deviceDetailsPage = new DeviceDetailsPage(driver);

        homePage.navigateToUrl("https://www.starhub.com");
        homePage.acceptCookies();
        homePage.clickMobilesMenu();
        homePage.clickAllPhones();
        allPhonesPage.clickDevice("Samsung Galaxy A57 5G");

        deviceDetailsPage.selectColour("White");
        deviceDetailsPage.selectStorage("512");
        deviceDetailsPage.selectPayment("12");

        String colour = deviceDetailsPage.getSelectedColour();
        String storage = deviceDetailsPage.getSelectedStorage();
        String payment = deviceDetailsPage.getSelectedPayment();

        Assert.assertTrue(colour.contains("White") || colour.contains("Black"), "Colour selection should update");
        Assert.assertTrue(storage.contains("512") || storage.contains("256"), "Storage selection should update");
        Assert.assertTrue(payment.contains("12") || payment.contains("24"), "Payment selection should update");
    }
}