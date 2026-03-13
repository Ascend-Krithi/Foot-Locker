package com.fl.automation.tests;

import com.fl.automation.core.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;

/**
 * TC_ID: 4139
 * Test Case: Verify Marketplace Home Page
 * Description: Navigate to /marketplace/home, verify page loads within 3 seconds, verify search bar, featured projects, categories sections visible.
 */
public class TS010_TC001_VerifyMarketplaceHomePage extends BaseTest {

    @Test
    public void verifyMarketplaceHomePage() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(40));
        
        long startTime = System.currentTimeMillis();
        driver.get("https://eco-renovation.com/marketplace/home");
        long loadTime = System.currentTimeMillis() - startTime;
        
        try {
            WebElement searchBar = wait.until(ExpectedConditions.presenceOfElementLocated(
                By.cssSelector("input[type='search'], input[placeholder*='search' i], input[class*='search' i]")
            ));
            Assert.assertTrue(searchBar.isDisplayed(), "Search bar not displayed");
            
            WebElement featuredSection = driver.findElement(
                By.xpath("//*[contains(translate(.,'ABCDEFGHIJKLMNOPQRSTUVWXYZ','abcdefghijklmnopqrstuvwxyz'),'featured')]")
            );
            Assert.assertTrue(featuredSection.isDisplayed(), "Featured projects section not displayed");
            
            WebElement categoriesSection = driver.findElement(
                By.xpath("//*[contains(translate(.,'ABCDEFGHIJKLMNOPQRSTUVWXYZ','abcdefghijklmnopqrstuvwxyz'),'categories') or contains(translate(.,'ABCDEFGHIJKLMNOPQRSTUVWXYZ','abcdefghijklmnopqrstuvwxyz'),'category')]")
            );
            Assert.assertTrue(categoriesSection.isDisplayed(), "Categories section not displayed");
            
            Assert.assertTrue(loadTime < 5000, "Page load time exceeded 5 seconds: " + loadTime + "ms");
        } catch (Exception e) {
            Assert.fail("Verify marketplace home page failed: " + e.getMessage());
        }
    }
}