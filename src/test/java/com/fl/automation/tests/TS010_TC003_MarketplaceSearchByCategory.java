package com.fl.automation.tests;

import com.aventstack.extentreports.Status;
import com.fl.automation.core.BaseTest;
import com.fl.automation.utils.ExtentManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;

public class TS010_TC003_MarketplaceSearchByCategory extends BaseTest {
    
    @Test(description = "TC4141: SCRUM-19509 TS-002 TC-002 - Marketplace search by category")
    public void marketplaceSearchByCategory() {
        ExtentManager.getTest().log(Status.INFO, "Starting test: Marketplace search by category");
        
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(50));
        
        ExtentManager.getTest().log(Status.INFO, "Navigating to marketplace");
        driver.get(MARKETPLACE_URL);
        ExtentManager.getTest().log(Status.PASS, "Marketplace loaded");
        
        ExtentManager.getTest().log(Status.INFO, "Selecting category filter");
        WebElement categoryDropdown = wait.until(ExpectedConditions.elementToBeClickable(By.id("categoryFilter")));
        categoryDropdown.click();
        ExtentManager.getTest().log(Status.PASS, "Category dropdown opened");
        
        ExtentManager.getTest().log(Status.INFO, "Selecting 'Solar Energy' category");
        WebElement solarCategory = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//option[contains(text(),'Solar Energy')]"))); 
        solarCategory.click();
        ExtentManager.getTest().log(Status.PASS, "Solar Energy category selected");
        
        ExtentManager.getTest().log(Status.INFO, "Applying filter");
        WebElement applyFilterBtn = driver.findElement(By.cssSelector(".apply-filter-btn"));
        applyFilterBtn.click();
        ExtentManager.getTest().log(Status.PASS, "Filter applied");
        
        ExtentManager.getTest().log(Status.INFO, "Verifying filtered results");
        List<WebElement> filteredResults = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector(".project-card")));
        Assert.assertTrue(filteredResults.size() > 0, "Filtered results should be displayed");
        ExtentManager.getTest().log(Status.PASS, "Found " + filteredResults.size() + " projects in Solar Energy category");
        
        ExtentManager.getTest().log(Status.INFO, "Test completed successfully");
    }
}