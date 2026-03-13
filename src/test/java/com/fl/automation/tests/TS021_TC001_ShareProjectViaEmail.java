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

public class TS021_TC001_ShareProjectViaEmail extends BaseTest {
    
    @Test(description = "TC4153: SCRUM-19509 TS-013 TC-001 - Share project via email")
    public void shareProjectViaEmail() {
        ExtentManager.getTest().log(Status.INFO, "Starting test: Share project via email");
        
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(50));
        
        ExtentManager.getTest().log(Status.INFO, "Navigating to project details");
        driver.get(MARKETPLACE_URL + "/project/12345");
        ExtentManager.getTest().log(Status.PASS, "Project details page loaded");
        
        ExtentManager.getTest().log(Status.INFO, "Clicking Share button");
        WebElement shareBtn = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".share-btn")));
        shareBtn.click();
        ExtentManager.getTest().log(Status.PASS, "Share menu opened");
        
        ExtentManager.getTest().log(Status.INFO, "Selecting Email option");
        WebElement emailOption = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".share-email")));
        emailOption.click();
        ExtentManager.getTest().log(Status.PASS, "Email share option selected");
        
        ExtentManager.getTest().log(Status.INFO, "Entering recipient email");
        WebElement recipientInput = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("recipientEmail")));
        recipientInput.sendKeys("colleague@example.com");
        
        WebElement messageInput = driver.findElement(By.id("shareMessage"));
        messageInput.sendKeys("Check out this interesting project!");
        
        ExtentManager.getTest().log(Status.PASS, "Share details entered");
        
        ExtentManager.getTest().log(Status.INFO, "Sending email");
        WebElement sendBtn = driver.findElement(By.cssSelector(".send-email-btn"));
        sendBtn.click();
        ExtentManager.getTest().log(Status.PASS, "Send button clicked");
        
        ExtentManager.getTest().log(Status.INFO, "Verifying email sent");
        WebElement successMessage = wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".success-message")));
        Assert.assertTrue(successMessage.isDisplayed(), "Email sent success message should be displayed");
        ExtentManager.getTest().log(Status.PASS, "Project shared via email successfully");
        
        ExtentManager.getTest().log(Status.INFO, "Test completed successfully");
    }
}