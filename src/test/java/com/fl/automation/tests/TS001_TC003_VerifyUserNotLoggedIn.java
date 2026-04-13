package com.fl.automation.tests;

import com.fl.automation.base.BaseTest;
import com.fl.automation.pages.AllPhonesPage;
import com.fl.automation.pages.HomePage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TS001_TC003_VerifyUserNotLoggedIn extends BaseTest {

    @Test
    public void testVerifyUserNotLoggedIn() {
        HomePage homePage = new HomePage(driver);
        AllPhonesPage allPhonesPage = new AllPhonesPage(driver);

        homePage.navigateToUrl("https://www.starhub.com");
        Assert.assertTrue(homePage.isPageLoaded(), "StarHub homepage did not load");

        homePage.acceptCookies();
        homePage.clickMobilesMenu();
        homePage.clickAllPhones();

        Assert.assertTrue(allPhonesPage.isAllPhonesPageLoaded(), "All Phones page did not load");
    }
}