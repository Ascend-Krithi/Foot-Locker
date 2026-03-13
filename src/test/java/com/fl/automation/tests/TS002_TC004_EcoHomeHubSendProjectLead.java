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

public class TS002_TC004_EcoHomeHubSendProjectLead extends BaseTest {
    
    @Test(description = "TC4107: SCRUM-19509 TS-001 TC-004 - Eco Home Hub send project lead")
    public void ecoHomeHubSendProjectLead() {
        ExtentManager.getTest().log(Status.INFO, "Starting test: Eco Home Hub send project lead");
        
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(50));
        
        ExtentManager.getTest().log(Status.INFO, "Navigating to installer profile");
        driver.get(ECO_HOME_HUB_URL + "/installer/12345");
        ExtentManager.getTest().log(Status.PASS, "Installer profile loaded");
        
        ExtentManager.getTest().log(Status.INFO, "Clicking Send Lead button");
        WebElement sendLeadBtn = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".send-lead-btn")));
        sendLeadBtn.click();
        ExtentManager.getTest().log(Status.PASS, "Send Lead form opened");
        
        ExtentManager.getTest().log(Status.INFO, "Filling project lead form");
        WebElement projectDescInput = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("projectDescription")));
        projectDescInput.sendKeys("Need solar panel installation for residential property");
        
        WebElement budgetInput = driver.findElement(By.id("budget"));
        budgetInput.sendKeys("50000");
        
        WebElement timelineInput = driver.findElement(By.id("timeline"));
        timelineInput.sendKeys("3 months");
        
        ExtentManager.getTest().log(Status.PASS, "Project lead form filled");
        
        ExtentManager.getTest().log(Status.INFO, "Submitting project lead");
        WebElement submitBtn = driver.findElement(By.cssSelector("button[type='submit']"));
        submitBtn.click();
        ExtentManager.getTest().log(Status.PASS, "Project lead submitted");
        
        ExtentManager.getTest().log(Status.INFO, "Verifying lead submission");
        WebElement successMessage = wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".success-message")));
        Assert.assertTrue(successMessage.isDisplayed(), "Success message should be displayed");
        ExtentManager.getTest().log(Status.PASS, "Project lead sent successfully");
        
        ExtentManager.getTest().log(Status.INFO, "Test completed successfully");
    }
}