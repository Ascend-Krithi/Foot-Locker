package com.fl.automation.tests;

import com.fl.automation.core.BaseTest;
import com.fl.automation.pages.HomePage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TS001_TC001_LaunchHomepageVerifyFindStore extends BaseTest {

    @Test(description = "TC4200 - Launch homepage, click Find a Store, verify popup and Select My Store link")
    public void testLaunchHomepageVerifyFindStore() throws InterruptedException {
        HomePage homePage = new HomePage(driver);

        homePage.acceptCookiesIfPresent();
        Thread.sleep(2000);

        Assert.assertTrue(driver.getCurrentUrl().contains("footlocker.com"), "Homepage did not load successfully");

        homePage.clickFindStore();
        Thread.sleep(2000);

        Assert.assertTrue(homePage.isStorePopupDisplayed(), "Store popup did not appear below Find a Store");

        Assert.assertTrue(homePage.isSelectMyStoreLinkVisible(), "Select My Store link is not visible within the popup");
    }
}