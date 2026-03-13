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
 * TC_ID: 4104
 * Test Case: Customer Registration
 * Description: Launch Eco Home Hub, navigate to registration, enter valid details, submit, verify success.
 */
public class TS002_TC001_CustomerRegistration extends BaseTest {

    @Test
    public void customerRegistration() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(40));
        
        driver.get("https://eco-home-hub.example.com");
        
        try {
            WebElement registerLink = wait.until(ExpectedConditions.presenceOfElementLocated(
                By.xpath("//a[contains(translate(.,'ABCDEFGHIJKLMNOPQRSTUVWXYZ','abcdefghijklmnopqrstuvwxyz'),'register')]"))
            );
            clickElement(registerLink);
            
            WebElement nameField = wait.until(ExpectedConditions.presenceOfElementLocated(
                By.cssSelector("input[name*='name' i], input[id*='name' i]")
            ));
            nameField.sendKeys("John Doe");
            
            WebElement emailField = driver.findElement(
                By.cssSelector("input[type='email'], input[name*='email' i]")
            );
            emailField.sendKeys("john.doe@email.com");
            
            WebElement passwordField = driver.findElement(
                By.cssSelector("input[type='password'], input[name*='password' i]")
            );
            passwordField.sendKeys("StrongPassword123");
            
            WebElement addressField = driver.findElement(
                By.cssSelector("input[name*='address' i], textarea[name*='address' i]")
            );
            addressField.sendKeys("123 Main St, London");
            
            WebElement submitButton = driver.findElement(
                By.cssSelector("button[type='submit'], input[type='submit']")
            );
            clickElement(submitButton);
            
            WebElement successMessage = wait.until(ExpectedConditions.presenceOfElementLocated(
                By.xpath("//*[contains(translate(.,'ABCDEFGHIJKLMNOPQRSTUVWXYZ','abcdefghijklmnopqrstuvwxyz'),'success') or contains(translate(.,'ABCDEFGHIJKLMNOPQRSTUVWXYZ','abcdefghijklmnopqrstuvwxyz'),'registered')]")
            ));
            
            Assert.assertTrue(successMessage.isDisplayed(), "Registration success message not displayed");
        } catch (Exception e) {
            Assert.fail("Customer registration failed: " + e.getMessage());
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