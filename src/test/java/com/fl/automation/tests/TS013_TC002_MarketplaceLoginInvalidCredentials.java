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

public class TS013_TC002_MarketplaceLoginInvalidCredentials extends BaseTest {
    
    @Test(description = "TC4145: SCRUM-19509 TS-005 TC-002 - Marketplace login invalid credentials")
    public void marketplaceLoginInvalidCredentials() {
        ExtentManager.getTest().log(Status.INFO, "Starting test: Marketplace login invalid credentials");
        
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(50));
        
        ExtentManager.getTest().log(Status.INFO, "Navigating to marketplace login page");
        driver.get(MARKETPLACE_URL + "/login");
        ExtentManager.getTest().log(Status.PASS, "Login page loaded");
        
        ExtentManager.getTest().log(Status.INFO, "Entering invalid credentials");
        WebElement emailInput = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("email")));
        emailInput.sendKeys("invalid@marketplace.com");
        
        WebElement passwordInput = driver.findElement(By.id("password"));
        passwordInput.sendKeys("WrongPassword123!");
        
        ExtentManager.getTest().log(Status.PASS, "Invalid credentials entered");
        
        ExtentManager.getTest().log(Status.INFO, "Clicking login button");
        WebElement loginBtn = driver.findElement(By.cssSelector("button[type='submit']"));
        loginBtn.click();
        ExtentManager.getTest().log(Status.PASS, "Login button clicked");
        
        ExtentManager.getTest().log(Status.INFO, "Verifying error message");
        WebElement errorMessage = wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".error-message")));
        Assert.assertTrue(errorMessage.isDisplayed(), "Error message should be displayed");
        Assert.assertTrue(errorMessage.getText().contains("Invalid"), "Error message should indicate invalid credentials");
        ExtentManager.getTest().log(Status.PASS, "Invalid credentials error displayed");
        
        ExtentManager.getTest().log(Status.INFO, "Test completed successfully");
    }
}