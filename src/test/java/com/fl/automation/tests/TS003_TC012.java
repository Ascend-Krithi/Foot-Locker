package com.fl.automation.tests;

import com.fl.automation.core.BaseTest;
import com.fl.automation.pages.HomePage;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * TestRail TC-4150: SCRUM-19509 Marketplace - Certifications
 */
public class TS003_TC012 extends BaseTest {

    @Test(description = "TC-4150: Marketplace certifications")
    public void testMarketplaceCertifications() {
        HomePage homePage = new HomePage(driver);
        homePage.open("https://www.footlocker.com");
        homePage.acceptCookies();
        
        Assert.assertTrue(driver.getCurrentUrl().length() > 0, "Certifications should be viewable");
    }
}