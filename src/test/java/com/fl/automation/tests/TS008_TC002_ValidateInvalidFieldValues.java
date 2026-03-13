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
 * TC_ID: 4121
 * Test Case: Validate Invalid Field Values
 * Description: Login as customer, navigate to loan application, enter invalid values, submit, verify error messages displayed and application not submitted.
 */
public class TS008_TC002_ValidateInvalidFieldValues extends BaseTest {

    @Test
    public void validateInvalidFieldValues() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(40));
        
        driver.get("https://eco-home-hub.example.com");
        
        try {
            WebElement loanLink = wait.until(ExpectedConditions.presenceOfElementLocated(
                By.xpath("//a[contains(translate(.,'ABCDEFGHIJKLMNOPQRSTUVWXYZ','abcdefghijklmnopqrstuvwxyz'),'loan')]")
            ));
            clickElement(loanLink);
            
            WebElement loanAmountField = wait.until(ExpectedConditions.presenceOfElementLocated(
                By.cssSelector("input[name*='amount' i], input[id*='amount' i]")
            ));
            loanAmountField.sendKeys("-5000");
            
            WebElement incomeField = driver.findElement(
                By.cssSelector("input[name*='income' i], input[id*='income' i]")
            );
            incomeField.sendKeys("abc");
            
            WebElement submitButton = driver.findElement(
                By.cssSelector("button[type='submit'], input[type='submit']")
            );
            clickElement(submitButton);
            
            WebElement errorMessage = wait.until(ExpectedConditions.presenceOfElementLocated(
                By.xpath("//*[contains(translate(.,'ABCDEFGHIJKLMNOPQRSTUVWXYZ','abcdefghijklmnopqrstuvwxyz'),'invalid') or contains(translate(.,'ABCDEFGHIJKLMNOPQRSTUVWXYZ','abcdefghijklmnopqrstuvwxyz'),'error')]")
            ));
            
            Assert.assertTrue(errorMessage.isDisplayed(), "Error message for invalid field values not displayed");
        } catch (Exception e) {
            Assert.fail("Validate invalid field values failed: " + e.getMessage());
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