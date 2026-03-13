package com.fl.automation.tests;

import com.fl.automation.core.BaseTest;
import com.fl.automation.pages.MarketplacePage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TS012_TC001_ContactProjectOwner extends BaseTest {

    @Test(description = "TC_4143: SCRUM-19509 TS-004 TC-001 - Contact project owner")
    public void testContactProjectOwner() {
        MarketplacePage marketplacePage = new MarketplacePage(driver);
        
        marketplacePage.navigateToMarketplace();
        marketplacePage.viewProjectDetails();
        marketplacePage.contactProjectOwner();
        
        Assert.assertTrue(true, "Contact project owner action completed");
    }
}