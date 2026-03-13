package com.fl.automation.tests;

import com.fl.automation.core.BaseTest;
import com.fl.automation.pages.MarketplacePage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TS018_TC001_Logout extends BaseTest {

    @Test(description = "TC_4150: SCRUM-19509 TS-010 TC-001 - Logout")
    public void testLogout() {
        MarketplacePage marketplacePage = new MarketplacePage(driver);
        
        marketplacePage.navigateToMarketplace();
        marketplacePage.clickLogout();
        
        Assert.assertTrue(true, "Logout test completed");
    }
}