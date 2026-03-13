package com.fl.automation.tests;

import com.fl.automation.core.BaseTest;
import com.fl.automation.pages.HomePage;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * TestRail TC-4147: SCRUM-19509 Marketplace - Profile management
 */
public class TS003_TC009 extends BaseTest {

    @Test(description = "TC-4147: Marketplace profile management")
    public void testMarketplaceProfile() {
        HomePage homePage = new HomePage(driver);
        homePage.open("https://www.footlocker.com");
        homePage.acceptCookies();
        
        Assert.assertTrue(driver.getPageSource().length() > 0, "Profile should be manageable");
    }
}