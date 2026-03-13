package com.fl.automation.tests;

import org.testng.annotations.Test;
import org.testng.Assert;
import com.fl.automation.core.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class TS005_TC002_Marketplace extends BaseTest {
    @Test
    public void testMarketplaceLoginInvalid() {
        driver.get("https://eco-renovation.com/marketplace/login");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(40));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("username"))).sendKeys("user1");
        driver.findElement(By.id("password")).sendKeys("wrongpass");
        driver.findElement(By.cssSelector("button[type='submit']")).click();
        boolean errorDisplayed = wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".error-message"))).isDisplayed();
        Assert.assertTrue(errorDisplayed, "Error message should be displayed");
    }
}