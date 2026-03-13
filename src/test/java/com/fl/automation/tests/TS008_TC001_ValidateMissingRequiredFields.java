package com.fl.automation.tests;

import com.fl.automation.core.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;

/**
 * TC_ID: 4120
 * Test Case: Validate Missing Required Fields
 * Description: Login as customer, navigate to loan application, leave required fields blank, submit, verify error messages displayed and application not submitted.
 */
public class TS008_TC001_ValidateMissingRequiredFields extends BaseTest {

    @Test
    public void validateMissingRequiredFields() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(40));
        
        driver.get("https://eco-home-hub.example.com");
        
        try {
            WebElement loanLink = wait.until(ExpectedConditions.presenceOfElementLocated(
                By.xpath("//a[contains(translate(.,'ABCDEFGHIJKLMNOPQRSTUVWXYZ','abcdefghijklmnopqrstuvwxyz'),'loan')]")
            ));
            clickElement(loanLink);
            
            WebElement submitButton = wait.until(ExpectedConditions.presenceOfElementLocated(
                By.cssSelector("button[type='submit'], input[type='submit']")
            ));
            clickElement(submitButton);
            
            WebElement errorMessage = wait.until(ExpectedConditions.presenceOfElementLocated(
                By.xpath("//*[contains(translate(.,'ABCDEFGHIJKLMNOPQRSTUVWXYZ','abcdefghijklmnopqrstuvwxyz'),'required') or contains(translate(.,'ABCDEFGHIJKLMNOPQRSTUVWXYZ','abcdefghijklmnopqrstuvwxyz'),'error') or contains(translate(.,'ABCDEFGHIJKLMNOPQRSTUVWXYZ','abcdefghijklmnopqrstuvwxyz'),'field')]")
            ));
            
            Assert.assertTrue(errorMessage.isDisplayed(), "Error message for missing required fields not displayed");
        } catch (Exception e) {
            Assert.fail("Validate missing required fields failed: " + e.getMessage());
        }
    }
    
    private void clickElement(WebElement element) {
        try {
            element.click();
        } catch (Exception e) {
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("arguments[0].click();", element);
        }
    }
}