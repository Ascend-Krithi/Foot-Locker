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

public class TS018_TC001_UserLogout extends BaseTest {
    
    @Test(description = "TC4150: SCRUM-19509 TS-010 TC-001 - User logout")
    public void userLogout() {
        ExtentManager.getTest().log(Status.INFO, "Starting test: User logout");
        
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(50));
        
        ExtentManager.getTest().log(Status.INFO, "Navigating to marketplace");
        driver.get(MARKETPLACE_URL);
        ExtentManager.getTest().log(Status.PASS, "Marketplace loaded");
        
        ExtentManager.getTest().log(Status.INFO, "Clicking user menu");
        WebElement userMenu = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".user-menu")));
        userMenu.click();
        ExtentManager.getTest().log(Status.PASS, "User menu opened");
        
        ExtentManager.getTest().log(Status.INFO, "Clicking Logout button");
        WebElement logoutBtn = wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Logout")));
        logoutBtn.click();
        ExtentManager.getTest().log(Status.PASS, "Logout button clicked");
        
        ExtentManager.getTest().log(Status.INFO, "Verifying logout success");
        WebElement loginLink = wait.until(ExpectedConditions.presenceOfElementLocated(By.linkText("Login")));
        Assert.assertTrue(loginLink.isDisplayed(), "Login link should be displayed after logout");
        ExtentManager.getTest().log(Status.PASS, "User logged out successfully");
        
        ExtentManager.getTest().log(Status.INFO, "Verifying session cleared");
        String currentUrl = driver.getCurrentUrl();
        Assert.assertFalse(currentUrl.contains("dashboard"), "User should not have access to dashboard after logout");
        ExtentManager.getTest().log(Status.PASS, "Session cleared successfully");
        
        ExtentManager.getTest().log(Status.INFO, "Test completed successfully");
    }
}