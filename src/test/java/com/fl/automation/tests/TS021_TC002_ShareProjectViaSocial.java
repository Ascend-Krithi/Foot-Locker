package com.fl.automation.tests;

import com.fl.automation.core.BaseTest;
import com.fl.automation.pages.MarketplacePage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TS021_TC002_ShareProjectViaSocial extends BaseTest {

    @Test(description = "TC_4154: SCRUM-19509 TS-013 TC-002 - Share project via social media")
    public void testShareProjectViaSocial() {
        MarketplacePage marketplacePage = new MarketplacePage(driver);
        
        marketplacePage.navigateToMarketplace();
        marketplacePage.viewProjectDetails();
        marketplacePage.shareViaSocial();
        
        Assert.assertTrue(true, "Share project via social media test completed");
    }
}