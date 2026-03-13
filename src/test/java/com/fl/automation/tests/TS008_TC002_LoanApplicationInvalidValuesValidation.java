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

public class TS008_TC002_LoanApplicationInvalidValuesValidation extends BaseTest {
    
    @Test(description = "TC4121: SCRUM-19509 TS-007 TC-002 - Loan application invalid values validation")
    public void loanApplicationInvalidValuesValidation() {
        ExtentManager.getTest().log(Status.INFO, "Starting test: Loan application invalid values validation");
        
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(50));
        
        ExtentManager.getTest().log(Status.INFO, "Navigating to loan application page");
        driver.get(ECO_HOME_HUB_URL + "/loan-application");
        ExtentManager.getTest().log(Status.PASS, "Loan application page loaded");
        
        ExtentManager.getTest().log(Status.INFO, "Entering invalid loan amount");
        WebElement loanAmountInput = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("loanAmount")));
        loanAmountInput.sendKeys("-5000");
        ExtentManager.getTest().log(Status.PASS, "Invalid loan amount entered");
        
        ExtentManager.getTest().log(Status.INFO, "Entering invalid annual income");
        WebElement annualIncomeInput = driver.findElement(By.id("annualIncome"));
        annualIncomeInput.sendKeys("abc123");
        ExtentManager.getTest().log(Status.PASS, "Invalid annual income entered");
        
        ExtentManager.getTest().log(Status.INFO, "Submitting form");
        WebElement submitBtn = driver.findElement(By.cssSelector("button[type='submit']"));
        submitBtn.click();
        ExtentManager.getTest().log(Status.PASS, "Submit button clicked");
        
        ExtentManager.getTest().log(Status.INFO, "Verifying validation error for negative loan amount");
        WebElement loanAmountError = wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".error-loanAmount")));
        Assert.assertTrue(loanAmountError.getText().contains("positive"), "Loan amount should be positive");
        ExtentManager.getTest().log(Status.PASS, "Negative loan amount validation error displayed");
        
        ExtentManager.getTest().log(Status.INFO, "Verifying validation error for invalid income format");
        WebElement incomeError = driver.findElement(By.cssSelector(".error-annualIncome"));
        Assert.assertTrue(incomeError.getText().contains("numeric"), "Annual income should be numeric");
        ExtentManager.getTest().log(Status.PASS, "Invalid income format validation error displayed");
        
        ExtentManager.getTest().log(Status.INFO, "Test completed successfully");
    }
}