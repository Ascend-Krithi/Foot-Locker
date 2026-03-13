package com.fl.automation.tests;

import org.testng.annotations.Test;
import org.testng.Assert;
import com.fl.automation.core.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class TS002_TC002_Marketplace extends BaseTest {
    @Test
    public void testMarketplaceSearchByCategory() {
        driver.get("https://eco-renovation.com/marketplace/home");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(40));
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(text(),'Insulation')]"))).click();
        boolean filteredResultsDisplayed = wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".search-results"))).isDisplayed();
        Assert.assertTrue(filteredResultsDisplayed, "Filtered search results should be displayed");
    }
}