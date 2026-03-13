package com.fl.automation.tests;

import org.testng.annotations.Test;
import org.testng.Assert;
import com.fl.automation.core.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class TS006_TC001_Marketplace extends BaseTest {
    @Test
    public void testMarketplaceRegistration() {
        driver.get("https://eco-renovation.com/marketplace/register");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(40));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("name"))).sendKeys("Jane Doe");
        driver.findElement(By.id("email")).sendKeys("jane@example.com");
        driver.findElement(By.id("password")).sendKeys("Pass@456");
        driver.findElement(By.cssSelector("button[type='submit']")).click();
        boolean confirmationDisplayed = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[contains(text(),'Registration successful')]"))).isDisplayed();
        Assert.assertTrue(confirmationDisplayed, "Registration should be successful and confirmation email sent");
    }
}