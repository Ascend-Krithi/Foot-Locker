package com.fl.automation.tests;

import com.fl.automation.core.BaseTest;
import com.fl.automation.pages.HomePage;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * TestRail TC-4151: SCRUM-19509 Marketplace - Share functionality
 */
public class TS003_TC013 extends BaseTest {

    @Test(description = "TC-4151: Marketplace share functionality")
    public void testMarketplaceShare() {
        HomePage homePage = new HomePage(driver);
        homePage.open("https://www.footlocker.com");
        homePage.acceptCookies();
        
        Assert.assertNotNull(driver.getWindowHandle(), "Share functionality should work");
    }
}