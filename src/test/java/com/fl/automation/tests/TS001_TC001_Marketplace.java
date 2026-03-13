package com.fl.automation.tests;

import org.testng.annotations.Test;
import org.testng.Assert;
import com.fl.automation.core.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class TS001_TC001_Marketplace extends BaseTest {
    @Test
    public void testMarketplaceHomePage() {
        driver.get("https://eco-renovation.com/marketplace/home");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(40));
        boolean searchBarVisible = wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("input[type='search']"))).isDisplayed();
        Assert.assertTrue(searchBarVisible, "Search bar should be visible");
        boolean featuredProjectsVisible = wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".featured-projects"))).isDisplayed();
        Assert.assertTrue(featuredProjectsVisible, "Featured projects section should be visible");
        boolean categoriesVisible = wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".categories"))).isDisplayed();
        Assert.assertTrue(categoriesVisible, "Categories section should be visible");
    }
}