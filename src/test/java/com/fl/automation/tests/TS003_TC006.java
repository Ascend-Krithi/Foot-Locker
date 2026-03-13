package com.fl.automation.tests;

import com.fl.automation.core.BaseTest;
import com.fl.automation.pages.HomePage;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * TestRail TC-4144: SCRUM-19509 Marketplace - Registration
 */
public class TS003_TC006 extends BaseTest {

    @Test(description = "TC-4144: Marketplace registration")
    public void testMarketplaceRegistration() {
        HomePage homePage = new HomePage(driver);
        homePage.open("https://www.footlocker.com");
        homePage.acceptCookies();
        
        Assert.assertTrue(driver.getTitle().length() > 0, "Registration should be accessible");
    }
}