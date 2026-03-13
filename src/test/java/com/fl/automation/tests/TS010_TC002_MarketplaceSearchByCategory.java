package com.fl.automation.tests;

import com.fl.automation.core.BaseTest;
import com.fl.automation.pages.MarketplacePage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TS010_TC002_MarketplaceSearchByCategory extends BaseTest {

    @Test(description = "TC_4141: SCRUM-19509 TS-002 TC-002 - Marketplace search by category filter")
    public void testMarketplaceSearchByCategory() {
        MarketplacePage marketplacePage = new MarketplacePage(driver);
        
        marketplacePage.navigateToMarketplace();
        marketplacePage.filterByCategory("Solar");
        
        boolean areProjectsDisplayed = marketplacePage.areProjectsDisplayed();
        Assert.assertTrue(areProjectsDisplayed, "Projects should be displayed after category filter");
    }
}