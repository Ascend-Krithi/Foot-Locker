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

public class TS010_TC002_MarketplaceSearchByKeyword extends BaseTest {
    
    @Test(description = "TC4140: SCRUM-19509 TS-002 TC-001 - Marketplace search by keyword")
    public void marketplaceSearchByKeyword() {
        ExtentManager.getTest().log(Status.INFO, "Starting test: Marketplace search by keyword");
        
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(50));
        
        ExtentManager.getTest().log(Status.INFO, "Navigating to marketplace");
        driver.get(MARKETPLACE_URL);
        ExtentManager.getTest().log(Status.PASS, "Marketplace loaded");
        
        ExtentManager.getTest().log(Status.INFO, "Entering search keyword");
        WebElement searchBox = wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("input[type='search']")));
        searchBox.sendKeys("solar panels");
        ExtentManager.getTest().log(Status.PASS, "Search keyword entered");
        
        ExtentManager.getTest().log(Status.INFO, "Clicking search button");
        WebElement searchBtn = driver.findElement(By.cssSelector("button[type='submit']"));
        searchBtn.click();
        ExtentManager.getTest().log(Status.PASS, "Search button clicked");
        
        ExtentManager.getTest().log(Status.INFO, "Verifying search results");
        List<WebElement> searchResults = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector(".search-result-item")));
        Assert.assertTrue(searchResults.size() > 0, "Search results should be displayed");
        ExtentManager.getTest().log(Status.PASS, "Found " + searchResults.size() + " search results");
        
        ExtentManager.getTest().log(Status.INFO, "Verifying results contain search keyword");
        boolean keywordFound = false;
        for (WebElement result : searchResults) {
            if (result.getText().toLowerCase().contains("solar")) {
                keywordFound = true;
                break;
            }
        }
        Assert.assertTrue(keywordFound, "Search results should contain the keyword");
        ExtentManager.getTest().log(Status.PASS, "Search results contain keyword");
        
        ExtentManager.getTest().log(Status.INFO, "Test completed successfully");
    }
}