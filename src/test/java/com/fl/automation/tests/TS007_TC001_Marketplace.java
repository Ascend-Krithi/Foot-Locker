package com.fl.automation.tests;

import org.testng.annotations.Test;
import org.testng.Assert;
import com.fl.automation.core.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class TS007_TC001_Marketplace extends BaseTest {
    @Test
    public void testMarketplaceDashboard() {
        driver.get("https://eco-renovation.com/marketplace/login");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(40));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("username"))).sendKeys("user1");
        driver.findElement(By.id("password")).sendKeys("Pass@123");
        driver.findElement(By.cssSelector("button[type='submit']")).click();
        wait.until(ExpectedConditions.urlContains("dashboard"));
        boolean savedProjectsVisible = wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".saved-projects"))).isDisplayed();
        Assert.assertTrue(savedProjectsVisible, "Saved projects section should be visible");
        boolean messagesVisible = wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".messages"))).isDisplayed();
        Assert.assertTrue(messagesVisible, "Messages section should be visible");
        boolean profileVisible = wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".profile"))).isDisplayed();
        Assert.assertTrue(profileVisible, "Profile section should be visible");
    }
}