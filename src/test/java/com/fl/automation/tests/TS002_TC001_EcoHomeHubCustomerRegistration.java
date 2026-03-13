package com.fl.automation.tests;

import com.aventstack.extentreports.Status;
import com.fl.automation.core.BaseTest;
import com.fl.automation.pages.HomePage;
import com.fl.automation.utils.ExtentManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;

public class TS002_TC001_EcoHomeHubCustomerRegistration extends BaseTest {
    
    @Test(description = "TC4104: SCRUM-19509 TS-001 TC-001 - Eco Home Hub customer registration")
    public void ecoHomeHubCustomerRegistration() {
        ExtentManager.getTest().log(Status.INFO, "Starting test: Eco Home Hub customer registration");
        
        HomePage homePage = new HomePage(driver);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(50));
        
        ExtentManager.getTest().log(Status.INFO, "Navigating to Eco Home Hub");
        driver.get(ECO_HOME_HUB_URL);
        ExtentManager.getTest().log(Status.PASS, "Eco Home Hub page loaded");
        
        ExtentManager.getTest().log(Status.INFO, "Clicking Register button");
        WebElement registerBtn = wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Register")));
        registerBtn.click();
        ExtentManager.getTest().log(Status.PASS, "Register page opened");
        
        ExtentManager.getTest().log(Status.INFO, "Filling registration form");
        WebElement firstNameInput = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("firstName")));
        firstNameInput.sendKeys("John");
        
        WebElement lastNameInput = driver.findElement(By.id("lastName"));
        lastNameInput.sendKeys("Doe");
        
        WebElement emailInput = driver.findElement(By.id("email"));
        emailInput.sendKeys("john.doe@example.com");
        
        WebElement passwordInput = driver.findElement(By.id("password"));
        passwordInput.sendKeys("SecurePass123!");
        
        ExtentManager.getTest().log(Status.PASS, "Registration form filled");
        
        ExtentManager.getTest().log(Status.INFO, "Submitting registration");
        WebElement submitBtn = driver.findElement(By.cssSelector("button[type='submit']"));
        submitBtn.click();
        ExtentManager.getTest().log(Status.PASS, "Registration submitted");
        
        ExtentManager.getTest().log(Status.INFO, "Verifying registration success");
        WebElement successMessage = wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".success-message")));
        Assert.assertTrue(successMessage.isDisplayed(), "Registration success message should be displayed");
        ExtentManager.getTest().log(Status.PASS, "Registration completed successfully");
        
        ExtentManager.getTest().log(Status.INFO, "Test completed successfully");
    }
}