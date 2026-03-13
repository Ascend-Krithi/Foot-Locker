package com.fl.automation.tests;

import com.aventstack.extentreports.Status;
import com.fl.automation.core.BaseTest;
import com.fl.automation.utils.ExtentManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;

public class TS013_TC001_MarketplaceLoginValidCredentials extends BaseTest {
    
    @Test(description = "TC4144: SCRUM-19509 TS-005 TC-001 - Marketplace login valid credentials")
    public void marketplaceLoginValidCredentials() {
        ExtentManager.getTest().log(Status.INFO, "Starting test: Marketplace login valid credentials");
        
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(50));
        
        ExtentManager.getTest().log(Status.INFO, "Navigating to marketplace login page");
        driver.get(MARKETPLACE_URL + "/login");
        ExtentManager.getTest().log(Status.PASS, "Login page loaded");
        
        ExtentManager.getTest().log(Status.INFO, "Entering valid credentials");
        WebElement emailInput = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("email")));
        emailInput.sendKeys("user@marketplace.com");
        
        WebElement passwordInput = driver.findElement(By.id("password"));
        passwordInput.sendKeys("ValidPass123!");
        
        ExtentManager.getTest().log(Status.PASS, "Credentials entered");
        
        ExtentManager.getTest().log(Status.INFO, "Clicking login button");
        WebElement loginBtn = driver.findElement(By.cssSelector("button[type='submit']"));
        loginBtn.click();
        ExtentManager.getTest().log(Status.PASS, "Login button clicked");
        
        ExtentManager.getTest().log(Status.INFO, "Verifying successful login");
        WebElement userProfile = wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".user-profile")));
        Assert.assertTrue(userProfile.isDisplayed(), "User profile should be displayed after login");
        ExtentManager.getTest().log(Status.PASS, "Login successful");
        
        ExtentManager.getTest().log(Status.INFO, "Test completed successfully");
    }
}