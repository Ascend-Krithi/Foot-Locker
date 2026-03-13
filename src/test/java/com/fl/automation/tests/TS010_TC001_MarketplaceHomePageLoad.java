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

public class TS010_TC001_MarketplaceHomePageLoad extends BaseTest {
    
    @Test(description = "TC4139: SCRUM-19509 TS-001 TC-001 - Marketplace home page load")
    public void marketplaceHomePageLoad() {
        ExtentManager.getTest().log(Status.INFO, "Starting test: Marketplace home page load");
        
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(50));
        
        ExtentManager.getTest().log(Status.INFO, "Navigating to marketplace home page");
        driver.get(MARKETPLACE_URL);
        ExtentManager.getTest().log(Status.PASS, "Marketplace URL loaded");
        
        ExtentManager.getTest().log(Status.INFO, "Verifying page title");
        wait.until(ExpectedConditions.titleContains("Marketplace"));
        String pageTitle = driver.getTitle();
        Assert.assertTrue(pageTitle.contains("Marketplace"), "Page title should contain 'Marketplace'");
        ExtentManager.getTest().log(Status.PASS, "Page title verified: " + pageTitle);
        
        ExtentManager.getTest().log(Status.INFO, "Verifying main content is displayed");
        WebElement mainContent = wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".main-content")));
        Assert.assertTrue(mainContent.isDisplayed(), "Main content should be displayed");
        ExtentManager.getTest().log(Status.PASS, "Main content displayed");
        
        ExtentManager.getTest().log(Status.INFO, "Verifying search functionality is available");
        WebElement searchBox = driver.findElement(By.cssSelector("input[type='search']"));
        Assert.assertTrue(searchBox.isDisplayed(), "Search box should be displayed");
        ExtentManager.getTest().log(Status.PASS, "Search functionality available");
        
        ExtentManager.getTest().log(Status.INFO, "Test completed successfully");
    }
}