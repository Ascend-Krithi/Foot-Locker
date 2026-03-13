package com.fl.automation.tests;

import com.fl.automation.core.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;

public class TS002_TC001_MarketplaceSearchByKeyword extends BaseTest {

    @Test(description = "TC_4140: SCRUM-19509 TS-002 TC-001 - Marketplace search by keyword")
    public void verifyMarketplaceSearchByKeyword() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(40));
        
        driver.get(baseUrl);
        
        WebElement searchBox = wait.until(ExpectedConditions.presenceOfElementLocated(
            By.cssSelector("input[type='search'], input[placeholder*='search' i]")));
        Assert.assertTrue(searchBox.isDisplayed(), "Search by keyword functionality should be available");
    }
}