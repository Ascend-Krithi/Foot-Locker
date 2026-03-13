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
 * TC_ID: 4146
 * Test Case: Register New User
 * Description: Navigate to /marketplace/register, enter valid registration info, submit, verify registration successful and confirmation email sent.
 */
public class TS013_TC001_RegisterNewUser extends BaseTest {

    @Test
    public void registerNewUser() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(40));
        
        driver.get("https://eco-renovation.com/marketplace/register");
        
        try {
            WebElement nameField = wait.until(ExpectedConditions.presenceOfElementLocated(
                By.cssSelector("input[name*='name' i], input[id*='name' i]")
            ));
            nameField.sendKeys("Jane Doe");
            
            WebElement emailField = driver.findElement(
                By.cssSelector("input[type='email'], input[name*='email' i]")
            );
            emailField.sendKeys("jane@example.com");
            
            WebElement passwordField = driver.findElement(
                By.cssSelector("input[type='password'], input[name*='password' i]")
            );
            passwordField.sendKeys("Pass@456");
            
            WebElement submitButton = driver.findElement(
                By.cssSelector("button[type='submit'], input[type='submit']")
            );
            clickElement(submitButton);
            
            WebElement successMessage = wait.until(ExpectedConditions.presenceOfElementLocated(
                By.xpath("//*[contains(translate(.,'ABCDEFGHIJKLMNOPQRSTUVWXYZ','abcdefghijklmnopqrstuvwxyz'),'success') or contains(translate(.,'ABCDEFGHIJKLMNOPQRSTUVWXYZ','abcdefghijklmnopqrstuvwxyz'),'registered') or contains(translate(.,'ABCDEFGHIJKLMNOPQRSTUVWXYZ','abcdefghijklmnopqrstuvwxyz'),'confirmation')]")
            ));
            
            Assert.assertTrue(successMessage.isDisplayed(), "Registration success message not displayed");
        } catch (Exception e) {
            Assert.fail("Register new user failed: " + e.getMessage());
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