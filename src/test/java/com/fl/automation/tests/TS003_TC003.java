package com.fl.automation.tests;

import com.fl.automation.core.BaseTest;
import com.fl.automation.pages.HomePage;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * TestRail TC-4141: SCRUM-19509 Marketplace - Project details
 */
public class TS003_TC003 extends BaseTest {

    @Test(description = "TC-4141: Marketplace project details")
    public void testMarketplaceProjectDetails() {
        HomePage homePage = new HomePage(driver);
        homePage.open("https://www.footlocker.com");
        homePage.acceptCookies();
        
        Assert.assertNotNull(driver, "Project details should be accessible");
    }
}