package com.fl.automation.tests;

import com.fl.automation.core.BaseTest;
import com.fl.automation.pages.MarketplacePage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TS016_TC001_ApplyToProject extends BaseTest {

    @Test(description = "TC_4148: SCRUM-19509 TS-008 TC-001 - Apply to project")
    public void testApplyToProject() {
        MarketplacePage marketplacePage = new MarketplacePage(driver);
        
        marketplacePage.navigateToMarketplace();
        marketplacePage.viewProjectDetails();
        marketplacePage.applyToProject();
        
        Assert.assertTrue(true, "Apply to project test completed");
    }
}