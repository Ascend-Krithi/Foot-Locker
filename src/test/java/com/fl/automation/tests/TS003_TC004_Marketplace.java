package com.fl.automation.tests;

import org.testng.annotations.Test;
import org.testng.Assert;
import com.fl.automation.core.BaseTest;
import com.fl.automation.pages.ProjectDetailsPage;

public class TS003_TC004_Marketplace extends BaseTest {
    
    @Test
    public void testContactSeller() {
        driver.get("https://marketplace.example.com/project/123");
        ProjectDetailsPage detailsPage = new ProjectDetailsPage(driver);
        detailsPage.clickContact();
        
        Assert.assertTrue(driver.getCurrentUrl().contains("contact") || driver.getPageSource().contains("Contact"), "Contact form should be displayed");
    }
}