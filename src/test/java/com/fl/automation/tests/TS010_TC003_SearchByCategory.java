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
 * TC_ID: 4141
 * Test Case: Search By Category
 * Description: Navigate to /marketplace/search, select category 'Insulation', click search, verify filtered results displayed.
 */
public class TS010_TC003_SearchByCategory extends BaseTest {

    @Test
    public void searchByCategory() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(40));
        
        driver.get("https://eco-renovation.com/marketplace/search");
        
        try {
            WebElement categoryDropdown = wait.until(ExpectedConditions.presenceOfElementLocated(
                By.cssSelector("select[name*='category' i], select[id*='category' i]")
            ));
            clickElement(categoryDropdown);
            
            WebElement insulationOption = driver.findElement(
                By.xpath("//option[contains(translate(.,'ABCDEFGHIJKLMNOPQRSTUVWXYZ','abcdefghijklmnopqrstuvwxyz'),'insulation')]")
            );
            clickElement(insulationOption);
            
            WebElement searchButton = driver.findElement(
                By.cssSelector("button[type='submit'], button[class*='search' i]")
            );
            clickElement(searchButton);
            
            WebElement filteredResults = wait.until(ExpectedConditions.presenceOfElementLocated(
                By.cssSelector("[class*='result'], [class*='filtered'], ul li")
            ));
            
            Assert.assertTrue(filteredResults.isDisplayed(), "Filtered results not displayed");
        } catch (Exception e) {
            Assert.fail("Search by category failed: " + e.getMessage());
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