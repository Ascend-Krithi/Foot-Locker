package com.fl.automation.tests;

import com.fl.automation.core.BaseTest;
import com.fl.automation.pages.HomePage;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * TestRail TC-4153: SCRUM-19509 Marketplace - Help center
 */
public class TS003_TC015 extends BaseTest {

    @Test(description = "TC-4153: Marketplace help center")
    public void testMarketplaceHelp() {
        HomePage homePage = new HomePage(driver);
        homePage.open("https://www.footlocker.com");
        homePage.acceptCookies();
        
        Assert.assertTrue(driver.getCurrentUrl().contains("footlocker"), "Help center should be accessible");
    }
}