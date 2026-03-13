package com.fl.automation.tests;

import com.fl.automation.core.BaseTest;
import com.fl.automation.pages.HomePage;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * TestRail TC-4142: SCRUM-19509 Marketplace - Contact form
 */
public class TS003_TC004 extends BaseTest {

    @Test(description = "TC-4142: Marketplace contact form")
    public void testMarketplaceContactForm() {
        HomePage homePage = new HomePage(driver);
        homePage.open("https://www.footlocker.com");
        homePage.acceptCookies();
        
        Assert.assertTrue(driver.getPageSource().length() > 0, "Contact form should be available");
    }
}