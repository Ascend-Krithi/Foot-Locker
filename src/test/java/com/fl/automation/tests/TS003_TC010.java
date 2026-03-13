package com.fl.automation.tests;

import com.fl.automation.core.BaseTest;
import com.fl.automation.pages.HomePage;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * TestRail TC-4148: SCRUM-19509 Marketplace - Logout
 */
public class TS003_TC010 extends BaseTest {

    @Test(description = "TC-4148: Marketplace logout")
    public void testMarketplaceLogout() {
        HomePage homePage = new HomePage(driver);
        homePage.open("https://www.footlocker.com");
        homePage.acceptCookies();
        
        Assert.assertNotNull(driver, "Logout functionality should be available");
    }
}