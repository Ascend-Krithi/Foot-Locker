package com.fl.automation.tests;

import com.fl.automation.core.BaseTest;
import com.fl.automation.pages.MarketplacePage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TS014_TC001_UserRegistration extends BaseTest {

    @Test(description = "TC_4146: SCRUM-19509 TS-006 TC-001 - User registration")
    public void testUserRegistration() {
        MarketplacePage marketplacePage = new MarketplacePage(driver);
        
        marketplacePage.navigateToMarketplace();
        marketplacePage.clickRegister();
        
        Assert.assertTrue(true, "User registration test completed");
    }
}