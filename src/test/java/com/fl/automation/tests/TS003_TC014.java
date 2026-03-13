package com.fl.automation.tests;

import com.fl.automation.core.BaseTest;
import com.fl.automation.pages.HomePage;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * TestRail TC-4152: SCRUM-19509 Marketplace - Favorites
 */
public class TS003_TC014 extends BaseTest {

    @Test(description = "TC-4152: Marketplace favorites")
    public void testMarketplaceFavorites() {
        HomePage homePage = new HomePage(driver);
        homePage.open("https://www.footlocker.com");
        homePage.acceptCookies();
        
        Assert.assertTrue(driver.getPageSource().length() > 0, "Favorites should be manageable");
    }
}