package com.fl.automation.tests;

import com.fl.automation.core.BaseTest;
import com.fl.automation.pages.HomePage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TS001_TC001_LaunchHomepageVerifyFindStore extends BaseTest {

    @Test(description = "TC4200 - Launch homepage and verify Find a Store link/button")
    public void testLaunchHomepageVerifyFindStore() {
        HomePage homePage = new HomePage(driver);
        
        homePage.acceptCookiesIfPresent();
        homePage.clickFindStore();
        
        Assert.assertTrue(homePage.isSelectMyStoreLinkDisplayed(), 
            "Select My Store link should be visible in the popup");
    }
}