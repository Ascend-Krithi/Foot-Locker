package com.fl.automation.tests;

import org.testng.annotations.Test;
import org.testng.Assert;
import com.fl.automation.core.BaseTest;
import com.fl.automation.pages.MarketplaceHomePage;

public class TS003_TC001_Marketplace extends BaseTest {
    
    @Test
    public void testMarketplaceHomePage() {
        driver.get("https://marketplace.example.com");
        MarketplaceHomePage homePage = new MarketplaceHomePage(driver);
        
        Assert.assertTrue(homePage.isHomePageDisplayed(), "Marketplace home page should be displayed");
    }
}