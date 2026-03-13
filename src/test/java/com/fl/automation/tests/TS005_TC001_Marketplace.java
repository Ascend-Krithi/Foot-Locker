package com.fl.automation.tests;

import org.testng.annotations.Test;
import org.testng.Assert;
import com.fl.automation.core.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class TS005_TC001_Marketplace extends BaseTest {
    @Test
    public void testMarketplaceLoginValid() {
        driver.get("https://eco-renovation.com/marketplace/login");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(40));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("username"))).sendKeys("user1");
        driver.findElement(By.id("password")).sendKeys("Pass@123");
        driver.findElement(By.cssSelector("button[type='submit']")).click();
        boolean dashboardLoaded = wait.until(ExpectedConditions.urlContains("dashboard"));
        Assert.assertTrue(dashboardLoaded, "User should be authenticated and redirected to dashboard");
    }
}