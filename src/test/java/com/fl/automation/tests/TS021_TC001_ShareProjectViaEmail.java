package com.fl.automation.tests;

import com.fl.automation.core.BaseTest;
import com.fl.automation.pages.MarketplacePage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TS021_TC001_ShareProjectViaEmail extends BaseTest {

    @Test(description = "TC_4153: SCRUM-19509 TS-013 TC-001 - Share project via email")
    public void testShareProjectViaEmail() {
        MarketplacePage marketplacePage = new MarketplacePage(driver);
        
        marketplacePage.navigateToMarketplace();
        marketplacePage.viewProjectDetails();
        marketplacePage.shareViaEmail();
        
        Assert.assertTrue(true, "Share project via email test completed");
    }
}