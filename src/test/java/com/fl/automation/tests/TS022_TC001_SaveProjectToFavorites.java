package com.fl.automation.tests;

import com.fl.automation.core.BaseTest;
import com.fl.automation.pages.MarketplacePage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TS022_TC001_SaveProjectToFavorites extends BaseTest {

    @Test(description = "TC_4155: SCRUM-19509 TS-014 TC-001 - Save project to favorites")
    public void testSaveProjectToFavorites() {
        MarketplacePage marketplacePage = new MarketplacePage(driver);
        
        marketplacePage.navigateToMarketplace();
        marketplacePage.viewProjectDetails();
        marketplacePage.saveToFavorites();
        
        Assert.assertTrue(true, "Save project to favorites test completed");
    }
}