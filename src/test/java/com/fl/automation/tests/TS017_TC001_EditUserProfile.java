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

public class TS017_TC001_EditUserProfile extends BaseTest {
    
    @Test(description = "TC4149: SCRUM-19509 TS-009 TC-001 - Edit user profile")
    public void editUserProfile() {
        ExtentManager.getTest().log(Status.INFO, "Starting test: Edit user profile");
        
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(50));
        
        ExtentManager.getTest().log(Status.INFO, "Navigating to user profile");
        driver.get(MARKETPLACE_URL + "/profile");
        ExtentManager.getTest().log(Status.PASS, "User profile page loaded");
        
        ExtentManager.getTest().log(Status.INFO, "Clicking Edit Profile button");
        WebElement editBtn = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".edit-profile-btn")));
        editBtn.click();
        ExtentManager.getTest().log(Status.PASS, "Edit mode activated");
        
        ExtentManager.getTest().log(Status.INFO, "Updating profile information");
        WebElement phoneInput = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("phone")));
        phoneInput.clear();
        phoneInput.sendKeys("555-9876");
        
        WebElement bioInput = driver.findElement(By.id("bio"));
        bioInput.clear();
        bioInput.sendKeys("Experienced contractor specializing in eco-friendly home improvements.");
        
        WebElement locationInput = driver.findElement(By.id("location"));
        locationInput.clear();
        locationInput.sendKeys("Boston, MA");
        
        ExtentManager.getTest().log(Status.PASS, "Profile information updated");
        
        ExtentManager.getTest().log(Status.INFO, "Saving profile changes");
        WebElement saveBtn = driver.findElement(By.cssSelector(".save-profile-btn"));
        saveBtn.click();
        ExtentManager.getTest().log(Status.PASS, "Save button clicked");
        
        ExtentManager.getTest().log(Status.INFO, "Verifying profile update success");
        WebElement successMessage = wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".success-message")));
        Assert.assertTrue(successMessage.isDisplayed(), "Profile update success message should be displayed");
        ExtentManager.getTest().log(Status.PASS, "Profile updated successfully");
        
        ExtentManager.getTest().log(Status.INFO, "Test completed successfully");
    }
}