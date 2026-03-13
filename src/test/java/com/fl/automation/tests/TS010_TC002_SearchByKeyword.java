package com.fl.automation.tests;

import com.fl.automation.core.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;

/**
 * TC_ID: 4140
 * Test Case: Search By Keyword
 * Description: Navigate to /marketplace/search, enter keyword 'solar', click search, verify relevant results displayed.
 */
public class TS010_TC002_SearchByKeyword extends BaseTest {

    @Test
    public void searchByKeyword() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(40));
        
        driver.get("https://eco-renovation.com/marketplace/search");
        
        try {
            WebElement searchInput = wait.until(ExpectedConditions.presenceOfElementLocated(
                By.cssSelector("input[type='search'], input[name*='search' i], input[placeholder*='search' i]")
            ));
            searchInput.sendKeys("solar");
            
            WebElement searchButton = driver.findElement(
                By.cssSelector("button[type='submit'], button[class*='search' i], input[type='submit']")
            );
            clickElement(searchButton);
            
            WebElement searchResults = wait.until(ExpectedConditions.presenceOfElementLocated(
                By.cssSelector("[class*='result'], [class*='search-result'], ul li, div[class*='list']")
            ));
            
            Assert.assertTrue(searchResults.isDisplayed(), "Search results not displayed");
        } catch (Exception e) {
            Assert.fail("Search by keyword failed: " + e.getMessage());
        }
    }
    
    private void clickElement(WebElement element) {
        try {
            element.click();
        } catch (Exception e) {
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("arguments[0].click();", element);
        }
    }
}