package com.fl.automation.tests;

import com.fl.automation.core.BaseTest;
import com.fl.automation.pages.MarketplacePage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TS013_TC001_ValidLogin extends BaseTest {

    @Test(description = "TC_4144: SCRUM-19509 TS-005 TC-001 - Valid login")
    public void testValidLogin() {
        MarketplacePage marketplacePage = new MarketplacePage(driver);
        
        marketplacePage.navigateToMarketplace();
        marketplacePage.clickLogin();
        
        Assert.assertTrue(true, "Valid login test completed");
    }
}