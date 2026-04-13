package com.fl.automation.tests;

import com.fl.automation.base.BaseTest;
import com.fl.automation.pages.AllPhonesPage;
import com.fl.automation.pages.HomePage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TS001_TC002_VerifyAtLeastOneDeviceListed extends BaseTest {

    @Test
    public void testVerifyAtLeastOneDeviceListed() {
        HomePage homePage = new HomePage(driver);
        AllPhonesPage allPhonesPage = new AllPhonesPage(driver);

        homePage.navigateToUrl("https://www.starhub.com");
        homePage.acceptCookies();
        homePage.clickMobilesMenu();
        homePage.clickAllPhones();

        Assert.assertTrue(allPhonesPage.isAtLeastOneDeviceListed(), "At least one device should be listed");
    }
}