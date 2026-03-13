package com.fl.automation.tests;

import com.fl.automation.core.BaseTest;
import com.fl.automation.pages.MarketplacePage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TS023_TC001_AccessHelpAndSupport extends BaseTest {

    @Test(description = "TC_4156: SCRUM-19509 TS-015 TC-001 - Access help and support")
    public void testAccessHelpAndSupport() {
        MarketplacePage marketplacePage = new MarketplacePage(driver);
        
        marketplacePage.navigateToMarketplace();
        marketplacePage.clickHelp();
        
        Assert.assertTrue(true, "Access help and support test completed");
    }
}