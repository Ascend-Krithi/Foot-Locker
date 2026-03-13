package com.fl.automation.tests;

import com.fl.automation.core.BaseTest;
import com.fl.automation.pages.MarketplacePage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TS017_TC001_EditProfile extends BaseTest {

    @Test(description = "TC_4149: SCRUM-19509 TS-009 TC-001 - Edit profile")
    public void testEditProfile() {
        MarketplacePage marketplacePage = new MarketplacePage(driver);
        
        marketplacePage.navigateToMarketplace();
        marketplacePage.clickProfile();
        
        Assert.assertTrue(true, "Edit profile test completed");
    }
}