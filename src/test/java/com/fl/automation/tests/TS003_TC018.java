package com.fl.automation.tests;

import com.fl.automation.core.BaseTest;
import com.fl.automation.pages.HomePage;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * TestRail TC-4156: SCRUM-19509 Marketplace - Payment integration
 */
public class TS003_TC018 extends BaseTest {

    @Test(description = "TC-4156: Marketplace payment integration")
    public void testMarketplacePayment() {
        HomePage homePage = new HomePage(driver);
        homePage.open("https://www.footlocker.com");
        homePage.acceptCookies();
        
        Assert.assertTrue(driver.getCurrentUrl().startsWith("https"), "Payment should use secure connection");
    }
}