package com.fl.automation.tests;

import org.testng.annotations.Test;
import org.testng.Assert;
import com.fl.automation.core.BaseTest;
import com.fl.automation.pages.ProjectDetailsPage;

public class TS003_TC010_Marketplace extends BaseTest {
    
    @Test
    public void testAddToFavorites() {
        driver.get("https://marketplace.example.com/project/123");
        ProjectDetailsPage detailsPage = new ProjectDetailsPage(driver);
        detailsPage.clickFavorite();
        
        Assert.assertTrue(driver.getPageSource().contains("favorite") || driver.getPageSource().contains("Favorite"), "Project should be added to favorites");
    }
}