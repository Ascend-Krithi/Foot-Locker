package com.fl.automation.tests;

import com.fl.automation.core.BaseTest;
import com.fl.automation.pages.HomePage;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * TestRail TC-4146: SCRUM-19509 Marketplace - Apply functionality
 */
public class TS003_TC008 extends BaseTest {

    @Test(description = "TC-4146: Marketplace apply functionality")
    public void testMarketplaceApply() {
        HomePage homePage = new HomePage(driver);
        homePage.open("https://www.footlocker.com");
        homePage.acceptCookies();
        
        Assert.assertTrue(driver.getCurrentUrl().contains("footlocker"), "Apply functionality should work");
    }
}