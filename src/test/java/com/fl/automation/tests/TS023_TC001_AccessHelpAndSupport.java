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

public class TS023_TC001_AccessHelpAndSupport extends BaseTest {
    
    @Test(description = "TC4156: SCRUM-19509 TS-015 TC-001 - Access help and support")
    public void accessHelpAndSupport() {
        ExtentManager.getTest().log(Status.INFO, "Starting test: Access help and support");
        
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(50));
        
        ExtentManager.getTest().log(Status.INFO, "Navigating to marketplace");
        driver.get(MARKETPLACE_URL);
        ExtentManager.getTest().log(Status.PASS, "Marketplace loaded");
        
        ExtentManager.getTest().log(Status.INFO, "Clicking Help & Support link");
        WebElement helpLink = wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Help & Support")));
        helpLink.click();
        ExtentManager.getTest().log(Status.PASS, "Help & Support link clicked");
        
        ExtentManager.getTest().log(Status.INFO, "Verifying Help Center page loaded");
        WebElement helpHeader = wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".help-header")));
        Assert.assertTrue(helpHeader.isDisplayed(), "Help Center header should be displayed");
        ExtentManager.getTest().log(Status.PASS, "Help Center page loaded");
        
        ExtentManager.getTest().log(Status.INFO, "Verifying FAQ section");
        WebElement faqSection = driver.findElement(By.cssSelector(".faq-section"));
        Assert.assertTrue(faqSection.isDisplayed(), "FAQ section should be displayed");
        ExtentManager.getTest().log(Status.PASS, "FAQ section verified");
        
        ExtentManager.getTest().log(Status.INFO, "Verifying Contact Support option");
        WebElement contactSupportBtn = driver.findElement(By.cssSelector(".contact-support-btn"));
        Assert.assertTrue(contactSupportBtn.isDisplayed(), "Contact Support button should be displayed");
        ExtentManager.getTest().log(Status.PASS, "Contact Support option verified");
        
        ExtentManager.getTest().log(Status.INFO, "Test completed successfully");
    }
}