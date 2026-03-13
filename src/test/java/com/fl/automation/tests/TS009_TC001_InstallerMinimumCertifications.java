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

public class TS009_TC001_InstallerMinimumCertifications extends BaseTest {
    
    @Test(description = "TC4122: SCRUM-19509 TS-008 TC-001 - Installer minimum certifications")
    public void installerMinimumCertifications() {
        ExtentManager.getTest().log(Status.INFO, "Starting test: Installer minimum certifications");
        
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(50));
        
        ExtentManager.getTest().log(Status.INFO, "Navigating to installer registration page");
        driver.get(ECO_HOME_HUB_URL + "/installer/register");
        ExtentManager.getTest().log(Status.PASS, "Installer registration page loaded");
        
        ExtentManager.getTest().log(Status.INFO, "Filling basic information");
        WebElement companyNameInput = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("companyName")));
        companyNameInput.sendKeys("Test Solar Company");
        
        WebElement emailInput = driver.findElement(By.id("email"));
        emailInput.sendKeys("test@solarpro.com");
        
        ExtentManager.getTest().log(Status.PASS, "Basic information filled");
        
        ExtentManager.getTest().log(Status.INFO, "Attempting to submit without certifications");
        WebElement submitBtn = driver.findElement(By.cssSelector("button[type='submit']"));
        submitBtn.click();
        ExtentManager.getTest().log(Status.PASS, "Submit button clicked");
        
        ExtentManager.getTest().log(Status.INFO, "Verifying minimum certification requirement error");
        WebElement certificationError = wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".error-certifications")));
        Assert.assertTrue(certificationError.getText().contains("minimum"), "Minimum certification error should be displayed");
        ExtentManager.getTest().log(Status.PASS, "Minimum certification requirement validated");
        
        ExtentManager.getTest().log(Status.INFO, "Test completed successfully");
    }
}