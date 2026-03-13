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

public class TS014_TC001_UserRegistration extends BaseTest {
    
    @Test(description = "TC4146: SCRUM-19509 TS-006 TC-001 - User registration")
    public void userRegistration() {
        ExtentManager.getTest().log(Status.INFO, "Starting test: User registration");
        
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(50));
        
        ExtentManager.getTest().log(Status.INFO, "Navigating to registration page");
        driver.get(MARKETPLACE_URL + "/register");
        ExtentManager.getTest().log(Status.PASS, "Registration page loaded");
        
        ExtentManager.getTest().log(Status.INFO, "Filling registration form");
        WebElement firstNameInput = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("firstName")));
        firstNameInput.sendKeys("Jane");
        
        WebElement lastNameInput = driver.findElement(By.id("lastName"));
        lastNameInput.sendKeys("Smith");
        
        WebElement emailInput = driver.findElement(By.id("email"));
        String uniqueEmail = "jane.smith." + System.currentTimeMillis() + "@example.com";
        emailInput.sendKeys(uniqueEmail);
        
        WebElement passwordInput = driver.findElement(By.id("password"));
        passwordInput.sendKeys("SecurePass123!");
        
        WebElement confirmPasswordInput = driver.findElement(By.id("confirmPassword"));
        confirmPasswordInput.sendKeys("SecurePass123!");
        
        ExtentManager.getTest().log(Status.PASS, "Registration form filled");
        
        ExtentManager.getTest().log(Status.INFO, "Submitting registration");
        WebElement submitBtn = driver.findElement(By.cssSelector("button[type='submit']"));
        submitBtn.click();
        ExtentManager.getTest().log(Status.PASS, "Registration submitted");
        
        ExtentManager.getTest().log(Status.INFO, "Verifying registration success");
        WebElement successMessage = wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".success-message")));
        Assert.assertTrue(successMessage.isDisplayed(), "Registration success message should be displayed");
        ExtentManager.getTest().log(Status.PASS, "User registered successfully");
        
        ExtentManager.getTest().log(Status.INFO, "Test completed successfully");
    }
}