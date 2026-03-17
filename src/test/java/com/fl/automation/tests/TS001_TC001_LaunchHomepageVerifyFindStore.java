package com.fl.automation.tests;

import com.fl.automation.core.BaseTest;
import com.fl.automation.pages.HomePage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TS001_TC001_LaunchHomepageVerifyFindStore extends BaseTest {

    @Test(description = "TC4200 - TS-001 TC-001: Launch homepage, click Find a Store, verify popup and Select My Store link")
    public void testLaunchHomepageAndVerifyFindStore() {
        HomePage homePage = new HomePage(driver);
        
        homePage.acceptCookiesIfPresent();
        
        Assert.assertTrue(homePage.isFindStoreLinkDisplayed(), "Find a Store link should be displayed on homepage");
        
        homePage.clickFindStore();
        
        Assert.assertTrue(homePage.isSelectMyStoreLinkDisplayed(), "Select My Store link should be displayed in popup");
    }
}