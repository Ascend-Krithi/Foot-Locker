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

public class TS004_TC001_InstallerPortalLogin extends BaseTest {
    
    @Test(description = "TC4111: SCRUM-19509 TS-003 TC-001 - Installer portal login")
    public void installerPortalLogin() {
        ExtentManager.getTest().log(Status.INFO, "Starting test: Installer portal login");
        
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(50));
        
        ExtentManager.getTest().log(Status.INFO, "Navigating to installer portal");
        driver.get(ECO_HOME_HUB_URL + "/installer/login");
        ExtentManager.getTest().log(Status.PASS, "Installer login page loaded");
        
        ExtentManager.getTest().log(Status.INFO, "Entering login credentials");
        WebElement emailInput = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("email")));
        emailInput.sendKeys("installer@solarpro.com");
        
        WebElement passwordInput = driver.findElement(By.id("password"));
        passwordInput.sendKeys("InstallerPass123!");
        
        ExtentManager.getTest().log(Status.PASS, "Credentials entered");
        
        ExtentManager.getTest().log(Status.INFO, "Clicking login button");
        WebElement loginBtn = driver.findElement(By.cssSelector("button[type='submit']"));
        loginBtn.click();
        ExtentManager.getTest().log(Status.PASS, "Login button clicked");
        
        ExtentManager.getTest().log(Status.INFO, "Verifying successful login");
        WebElement dashboardHeader = wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".dashboard-header")));
        Assert.assertTrue(dashboardHeader.isDisplayed(), "Dashboard should be displayed after login");
        ExtentManager.getTest().log(Status.PASS, "Installer logged in successfully");
        
        ExtentManager.getTest().log(Status.INFO, "Test completed successfully");
    }
}