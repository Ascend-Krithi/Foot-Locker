package com.fl.automation.tests;

import com.fl.automation.core.BaseTest;
import com.fl.automation.pages.MarketplacePage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TS009_TC001_MarketplaceHomePageLoad extends BaseTest {

    @Test(description = "TC_4139: SCRUM-19509 TS-001 TC-001 - Marketplace home page load and elements")
    public void testMarketplaceHomePageLoad() {
        MarketplacePage marketplacePage = new MarketplacePage(driver);
        
        marketplacePage.navigateToMarketplace();
        
        boolean isPageLoaded = marketplacePage.isPageLoaded();
        Assert.assertTrue(isPageLoaded, "Marketplace page should be loaded");
    }
}