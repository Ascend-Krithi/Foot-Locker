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

public class TS012_TC001_ContactProjectOwner extends BaseTest {
    
    @Test(description = "TC4143: SCRUM-19509 TS-004 TC-001 - Contact project owner")
    public void contactProjectOwner() {
        ExtentManager.getTest().log(Status.INFO, "Starting test: Contact project owner");
        
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(50));
        
        ExtentManager.getTest().log(Status.INFO, "Navigating to project details page");
        driver.get(MARKETPLACE_URL + "/project/12345");
        ExtentManager.getTest().log(Status.PASS, "Project details page loaded");
        
        ExtentManager.getTest().log(Status.INFO, "Clicking Contact Owner button");
        WebElement contactBtn = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".contact-owner-btn")));
        contactBtn.click();
        ExtentManager.getTest().log(Status.PASS, "Contact form opened");
        
        ExtentManager.getTest().log(Status.INFO, "Filling contact form");
        WebElement messageInput = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("message")));
        messageInput.sendKeys("I am interested in your project and would like to discuss further.");
        
        WebElement emailInput = driver.findElement(By.id("contactEmail"));
        emailInput.sendKeys("contractor@example.com");
        
        WebElement phoneInput = driver.findElement(By.id("contactPhone"));
        phoneInput.sendKeys("555-1234");
        
        ExtentManager.getTest().log(Status.PASS, "Contact form filled");
        
        ExtentManager.getTest().log(Status.INFO, "Submitting contact form");
        WebElement submitBtn = driver.findElement(By.cssSelector("button[type='submit']"));
        submitBtn.click();
        ExtentManager.getTest().log(Status.PASS, "Contact form submitted");
        
        ExtentManager.getTest().log(Status.INFO, "Verifying success message");
        WebElement successMessage = wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".success-message")));
        Assert.assertTrue(successMessage.isDisplayed(), "Success message should be displayed");
        ExtentManager.getTest().log(Status.PASS, "Contact message sent successfully");
        
        ExtentManager.getTest().log(Status.INFO, "Test completed successfully");
    }
}