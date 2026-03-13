package com.fl.automation.tests;

import com.fl.automation.core.BaseTest;
import com.fl.automation.pages.MarketplacePage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TS011_TC001_ViewProjectDetails extends BaseTest {

    @Test(description = "TC_4142: SCRUM-19509 TS-003 TC-001 - View project details")
    public void testViewProjectDetails() {
        MarketplacePage marketplacePage = new MarketplacePage(driver);
        
        marketplacePage.navigateToMarketplace();
        marketplacePage.viewProjectDetails();
        
        boolean isDetailsDisplayed = marketplacePage.isProjectDetailsDisplayed();
        Assert.assertTrue(isDetailsDisplayed, "Project details should be displayed");
    }
}