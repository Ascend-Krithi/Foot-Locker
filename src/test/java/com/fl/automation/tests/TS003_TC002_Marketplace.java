package com.fl.automation.tests;

import org.testng.annotations.Test;
import org.testng.Assert;
import com.fl.automation.core.BaseTest;
import com.fl.automation.pages.MarketplaceHomePage;
import com.fl.automation.pages.SearchPage;

public class TS003_TC002_Marketplace extends BaseTest {
    
    @Test
    public void testMarketplaceSearch() {
        driver.get("https://marketplace.example.com");
        MarketplaceHomePage homePage = new MarketplaceHomePage(driver);
        homePage.searchProjects("solar panels");
        
        SearchPage searchPage = new SearchPage(driver);
        Assert.assertTrue(searchPage.areResultsDisplayed(), "Search results should be displayed");
    }
}