package com.fl.automation.tests;

import org.testng.annotations.Test;
import org.testng.Assert;
import com.fl.automation.core.BaseTest;
import com.fl.automation.pages.ProjectDetailsPage;

public class TS003_TC003_Marketplace extends BaseTest {
    
    @Test
    public void testProjectDetails() {
        driver.get("https://marketplace.example.com/project/123");
        ProjectDetailsPage detailsPage = new ProjectDetailsPage(driver);
        
        Assert.assertTrue(detailsPage.isProjectDetailsDisplayed(), "Project details should be displayed");
    }
}