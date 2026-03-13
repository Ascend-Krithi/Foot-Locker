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

public class TS008_TC001_LoanApplicationMissingFieldsValidation extends BaseTest {
    
    @Test(description = "TC4120: SCRUM-19509 TS-007 TC-001 - Loan application missing fields validation")
    public void loanApplicationMissingFieldsValidation() {
        ExtentManager.getTest().log(Status.INFO, "Starting test: Loan application missing fields validation");
        
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(50));
        
        ExtentManager.getTest().log(Status.INFO, "Navigating to loan application page");
        driver.get(ECO_HOME_HUB_URL + "/loan-application");
        ExtentManager.getTest().log(Status.PASS, "Loan application page loaded");
        
        ExtentManager.getTest().log(Status.INFO, "Submitting form without filling required fields");
        WebElement submitBtn = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("button[type='submit']")));
        submitBtn.click();
        ExtentManager.getTest().log(Status.PASS, "Submit button clicked");
        
        ExtentManager.getTest().log(Status.INFO, "Verifying validation error for loan amount");
        WebElement loanAmountError = wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".error-loanAmount")));
        Assert.assertTrue(loanAmountError.isDisplayed(), "Loan amount validation error should be displayed");
        ExtentManager.getTest().log(Status.PASS, "Loan amount validation error displayed");
        
        ExtentManager.getTest().log(Status.INFO, "Verifying validation error for property address");
        WebElement addressError = driver.findElement(By.cssSelector(".error-propertyAddress"));
        Assert.assertTrue(addressError.isDisplayed(), "Property address validation error should be displayed");
        ExtentManager.getTest().log(Status.PASS, "Property address validation error displayed");
        
        ExtentManager.getTest().log(Status.INFO, "Verifying validation error for annual income");
        WebElement incomeError = driver.findElement(By.cssSelector(".error-annualIncome"));
        Assert.assertTrue(incomeError.isDisplayed(), "Annual income validation error should be displayed");
        ExtentManager.getTest().log(Status.PASS, "Annual income validation error displayed");
        
        ExtentManager.getTest().log(Status.INFO, "Test completed successfully");
    }
}