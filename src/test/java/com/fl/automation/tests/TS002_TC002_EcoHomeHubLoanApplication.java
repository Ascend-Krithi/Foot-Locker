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

public class TS002_TC002_EcoHomeHubLoanApplication extends BaseTest {
    
    @Test(description = "TC4105: SCRUM-19509 TS-001 TC-002 - Eco Home Hub loan application")
    public void ecoHomeHubLoanApplication() {
        ExtentManager.getTest().log(Status.INFO, "Starting test: Eco Home Hub loan application");
        
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(50));
        
        ExtentManager.getTest().log(Status.INFO, "Navigating to Eco Home Hub loan application");
        driver.get(ECO_HOME_HUB_URL + "/loan-application");
        ExtentManager.getTest().log(Status.PASS, "Loan application page loaded");
        
        ExtentManager.getTest().log(Status.INFO, "Filling loan application form");
        WebElement loanAmountInput = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("loanAmount")));
        loanAmountInput.sendKeys("50000");
        
        WebElement projectTypeSelect = driver.findElement(By.id("projectType"));
        projectTypeSelect.sendKeys("Solar Panel Installation");
        
        WebElement propertyAddressInput = driver.findElement(By.id("propertyAddress"));
        propertyAddressInput.sendKeys("123 Main St, Boston, MA 02101");
        
        WebElement annualIncomeInput = driver.findElement(By.id("annualIncome"));
        annualIncomeInput.sendKeys("75000");
        
        ExtentManager.getTest().log(Status.PASS, "Loan application form filled");
        
        ExtentManager.getTest().log(Status.INFO, "Submitting loan application");
        WebElement submitBtn = driver.findElement(By.cssSelector("button[type='submit']"));
        submitBtn.click();
        ExtentManager.getTest().log(Status.PASS, "Loan application submitted");
        
        ExtentManager.getTest().log(Status.INFO, "Verifying application submission");
        WebElement confirmationMessage = wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".confirmation-message")));
        Assert.assertTrue(confirmationMessage.isDisplayed(), "Confirmation message should be displayed");
        ExtentManager.getTest().log(Status.PASS, "Loan application submitted successfully");
        
        ExtentManager.getTest().log(Status.INFO, "Test completed successfully");
    }
}