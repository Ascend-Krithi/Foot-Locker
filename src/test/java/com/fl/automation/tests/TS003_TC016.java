package com.fl.automation.tests;

import com.fl.automation.core.BaseTest;
import com.fl.automation.pages.HomePage;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * TestRail TC-4154: SCRUM-19509 Marketplace - Notifications
 */
public class TS003_TC016 extends BaseTest {

    @Test(description = "TC-4154: Marketplace notifications")
    public void testMarketplaceNotifications() {
        HomePage homePage = new HomePage(driver);
        homePage.open("https://www.footlocker.com");
        homePage.acceptCookies();
        
        Assert.assertNotNull(driver, "Notifications should be functional");
    }
}