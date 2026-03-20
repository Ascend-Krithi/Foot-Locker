package com.fl.automation.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import com.fl.automation.core.BaseTest;

public class TS001_TC001_LaunchHomepageVerifyFindStore extends BaseTest {
    
    @Test(description = "TC_4200: Launch homepage and verify Find a Store link is displayed")
    public void testLaunchHomepageVerifyFindStore() {
        homePage.acceptCookiesIfPresent();
        Assert.assertTrue(homePage.isFindStoreLinkDisplayed(), "Find a Store link should be visible on homepage");
        Assert.assertTrue(homePage.isOnFootLockerDomain(), "Should be on Foot Locker domain");
    }
}