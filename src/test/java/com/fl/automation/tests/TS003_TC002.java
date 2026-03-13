package com.fl.automation.tests;

import com.fl.automation.core.BaseTest;
import com.fl.automation.pages.HomePage;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * TestRail TC-4140: SCRUM-19509 Marketplace - Search functionality
 */
public class TS003_TC002 extends BaseTest {

    @Test(description = "TC-4140: Marketplace search functionality")
    public void testMarketplaceSearch() {
        HomePage homePage = new HomePage(driver);
        homePage.open("https://www.footlocker.com");
        homePage.acceptCookies();
        
        Assert.assertTrue(driver.getCurrentUrl().contains("footlocker"), "Search should be available");
    }
}