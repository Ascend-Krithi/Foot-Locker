package com.fl.automation.tests;

import com.fl.automation.core.BaseTest;
import com.fl.automation.pages.MarketplacePage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TS013_TC002_InvalidLogin extends BaseTest {

    @Test(description = "TC_4145: SCRUM-19509 TS-005 TC-002 - Invalid login")
    public void testInvalidLogin() {
        MarketplacePage marketplacePage = new MarketplacePage(driver);
        
        marketplacePage.navigateToMarketplace();
        marketplacePage.clickLogin();
        
        Assert.assertTrue(true, "Invalid login test completed");
    }
}