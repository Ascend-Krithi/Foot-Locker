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

public class TS016_TC001_ApplyToProject extends BaseTest {
    
    @Test(description = "TC4148: SCRUM-19509 TS-008 TC-001 - Apply to project")
    public void applyToProject() {
        ExtentManager.getTest().log(Status.INFO, "Starting test: Apply to project");
        
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(50));
        
        ExtentManager.getTest().log(Status.INFO, "Navigating to project details");
        driver.get(MARKETPLACE_URL + "/project/12345");
        ExtentManager.getTest().log(Status.PASS, "Project details page loaded");
        
        ExtentManager.getTest().log(Status.INFO, "Clicking Apply button");
        WebElement applyBtn = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".apply-btn")));
        applyBtn.click();
        ExtentManager.getTest().log(Status.PASS, "Apply button clicked");
        
        ExtentManager.getTest().log(Status.INFO, "Filling application form");
        WebElement proposalInput = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("proposal")));
        proposalInput.sendKeys("I have 10 years of experience in solar panel installation and would be perfect for this project.");
        
        WebElement estimatedCostInput = driver.findElement(By.id("estimatedCost"));
        estimatedCostInput.sendKeys("45000");
        
        WebElement timelineInput = driver.findElement(By.id("timeline"));
        timelineInput.sendKeys("2 months");
        
        ExtentManager.getTest().log(Status.PASS, "Application form filled");
        
        ExtentManager.getTest().log(Status.INFO, "Submitting application");
        WebElement submitBtn = driver.findElement(By.cssSelector("button[type='submit']"));
        submitBtn.click();
        ExtentManager.getTest().log(Status.PASS, "Application submitted");
        
        ExtentManager.getTest().log(Status.INFO, "Verifying application success");
        WebElement successMessage = wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".success-message")));
        Assert.assertTrue(successMessage.isDisplayed(), "Application success message should be displayed");
        ExtentManager.getTest().log(Status.PASS, "Application submitted successfully");
        
        ExtentManager.getTest().log(Status.INFO, "Test completed successfully");
    }
}