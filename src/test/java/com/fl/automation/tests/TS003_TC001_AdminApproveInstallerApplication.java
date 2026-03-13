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

public class TS003_TC001_AdminApproveInstallerApplication extends BaseTest {
    
    @Test(description = "TC4108: SCRUM-19509 TS-002 TC-001 - Admin approve installer application")
    public void adminApproveInstallerApplication() {
        ExtentManager.getTest().log(Status.INFO, "Starting test: Admin approve installer application");
        
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(50));
        
        ExtentManager.getTest().log(Status.INFO, "Navigating to admin portal");
        driver.get(ECO_HOME_HUB_URL + "/admin/login");
        ExtentManager.getTest().log(Status.PASS, "Admin login page loaded");
        
        ExtentManager.getTest().log(Status.INFO, "Logging in as admin");
        WebElement usernameInput = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("username")));
        usernameInput.sendKeys("admin@ecohomehub.com");
        
        WebElement passwordInput = driver.findElement(By.id("password"));
        passwordInput.sendKeys("AdminPass123!");
        
        WebElement loginBtn = driver.findElement(By.cssSelector("button[type='submit']"));
        loginBtn.click();
        ExtentManager.getTest().log(Status.PASS, "Admin logged in");
        
        ExtentManager.getTest().log(Status.INFO, "Navigating to pending applications");
        WebElement pendingAppsLink = wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Pending Applications")));
        pendingAppsLink.click();
        ExtentManager.getTest().log(Status.PASS, "Pending applications page loaded");
        
        ExtentManager.getTest().log(Status.INFO, "Approving first application");
        WebElement approveBtn = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".approve-btn")));
        approveBtn.click();
        ExtentManager.getTest().log(Status.PASS, "Approve button clicked");
        
        ExtentManager.getTest().log(Status.INFO, "Confirming approval");
        WebElement confirmBtn = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".confirm-approve-btn")));
        confirmBtn.click();
        ExtentManager.getTest().log(Status.PASS, "Approval confirmed");
        
        ExtentManager.getTest().log(Status.INFO, "Verifying approval success");
        WebElement successMessage = wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".success-message")));
        Assert.assertTrue(successMessage.isDisplayed(), "Success message should be displayed");
        ExtentManager.getTest().log(Status.PASS, "Installer application approved successfully");
        
        ExtentManager.getTest().log(Status.INFO, "Test completed successfully");
    }
}