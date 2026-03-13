package com.fl.automation.tests;

import com.fl.automation.core.BaseTest;
import com.fl.automation.pages.MarketplacePage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TS010_TC001_MarketplaceSearchByKeyword extends BaseTest {

    @Test(description = "TC_4140: SCRUM-19509 TS-002 TC-001 - Marketplace search by keyword")
    public void testMarketplaceSearchByKeyword() {
        MarketplacePage marketplacePage = new MarketplacePage(driver);
        
        marketplacePage.navigateToMarketplace();
        marketplacePage.searchByKeyword("solar panels");
        
        boolean areProjectsDisplayed = marketplacePage.areProjectsDisplayed();
        Assert.assertTrue(areProjectsDisplayed, "Projects should be displayed after search");
    }
}