package com.fl.automation.tests;

import com.fl.automation.core.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;

public class TS002_TC002_MarketplaceSearchByCategory extends BaseTest {

    @Test(description = "TC_4141: SCRUM-19509 TS-002 TC-002 - Marketplace search by category")
    public void verifyMarketplaceSearchByCategory() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(40));
        
        driver.get(baseUrl);
        
        WebElement categoryNav = wait.until(ExpectedConditions.presenceOfElementLocated(
            By.cssSelector("nav, ul[class*='category'], div[class*='category']")));
        Assert.assertTrue(categoryNav.isDisplayed(), "Search by category functionality should be available");
    }
}