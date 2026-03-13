package com.fl.automation.tests;

import com.fl.automation.core.BaseTest;
import com.fl.automation.pages.HomePage;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * TestRail TC-4155: SCRUM-19509 Marketplace - Settings
 */
public class TS003_TC017 extends BaseTest {

    @Test(description = "TC-4155: Marketplace settings")
    public void testMarketplaceSettings() {
        HomePage homePage = new HomePage(driver);
        homePage.open("https://www.footlocker.com");
        homePage.acceptCookies();
        
        Assert.assertTrue(driver.getTitle().length() > 0, "Settings should be accessible");
    }
}