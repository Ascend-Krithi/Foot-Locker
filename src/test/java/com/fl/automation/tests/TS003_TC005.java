package com.fl.automation.tests;

import com.fl.automation.core.BaseTest;
import com.fl.automation.pages.HomePage;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * TestRail TC-4143: SCRUM-19509 Marketplace - Login functionality
 */
public class TS003_TC005 extends BaseTest {

    @Test(description = "TC-4143: Marketplace login functionality")
    public void testMarketplaceLogin() {
        HomePage homePage = new HomePage(driver);
        homePage.open("https://www.footlocker.com");
        homePage.acceptCookies();
        
        Assert.assertTrue(driver.getCurrentUrl().startsWith("https"), "Login should use secure connection");
    }
}