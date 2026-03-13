package com.fl.automation.tests;

import org.testng.annotations.Test;
import org.testng.Assert;
import com.fl.automation.core.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class TS002_TC001_Marketplace extends BaseTest {
    @Test
    public void testMarketplaceSearchByKeyword() {
        driver.get("https://eco-renovation.com/marketplace/home");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(40));
        WebElement searchInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input[type='search']")));
        searchInput.sendKeys("solar");
        driver.findElement(By.cssSelector("button[type='submit']")).click();
        boolean resultsDisplayed = wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".search-results"))).isDisplayed();
        Assert.assertTrue(resultsDisplayed, "Search results should be displayed");
    }
}