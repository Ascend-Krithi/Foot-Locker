package com.fl.automation.tests;

import com.fl.automation.base.BaseTest;
import com.fl.automation.pages.AllPhonesPage;
import com.fl.automation.pages.HomePage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TS002_TC002_AttemptSelectNonexistentDevice extends BaseTest {

    @Test
    public void testAttemptSelectNonexistentDevice() {
        HomePage homePage = new HomePage(driver);
        AllPhonesPage allPhonesPage = new AllPhonesPage(driver);

        homePage.navigateToUrl("https://www.starhub.com");
        homePage.acceptCookies();
        homePage.clickMobilesMenu();
        homePage.clickAllPhones();

        Assert.assertFalse(allPhonesPage.isDeviceVisible("Nonexistent Phone Model"), "Nonexistent device should not be found");
        allPhonesPage.clickDevice("Nonexistent Phone Model");

        Assert.assertTrue(allPhonesPage.isAllPhonesPageLoaded(), "User should remain on All Phones page");
    }
}