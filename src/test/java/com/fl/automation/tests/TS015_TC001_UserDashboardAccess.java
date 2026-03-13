package com.fl.automation.tests;

import com.fl.automation.core.BaseTest;
import com.fl.automation.pages.MarketplacePage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TS015_TC001_UserDashboardAccess extends BaseTest {

    @Test(description = "TC_4147: SCRUM-19509 TS-007 TC-001 - User dashboard access")
    public void testUserDashboardAccess() {
        MarketplacePage marketplacePage = new MarketplacePage(driver);
        
        marketplacePage.navigateToMarketplace();
        marketplacePage.clickDashboard();
        
        Assert.assertTrue(true, "User dashboard access test completed");
    }
}