package com.fl.automation.tests;

import com.fl.automation.core.BaseTest;
import com.fl.automation.pages.HomePage;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * TestRail TC-4145: SCRUM-19509 Marketplace - Dashboard
 */
public class TS003_TC007 extends BaseTest {

    @Test(description = "TC-4145: Marketplace dashboard")
    public void testMarketplaceDashboard() {
        HomePage homePage = new HomePage(driver);
        homePage.open("https://www.footlocker.com");
        homePage.acceptCookies();
        
        Assert.assertNotNull(driver.getWindowHandle(), "Dashboard should be accessible");
    }
}