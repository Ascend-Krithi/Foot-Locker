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
 * TC_ID: 4105
 * Test Case: Loan Application
 * Description: Register customer, login, navigate to loan application, enter valid data, submit, verify confirmation.
 */
public class TS002_TC002_LoanApplication extends BaseTest {

    @Test
    public void loanApplication() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(40));
        
        driver.get("https://eco-home-hub.example.com");
        
        try {
            WebElement loanLink = wait.until(ExpectedConditions.presenceOfElementLocated(
                By.xpath("//a[contains(translate(.,'ABCDEFGHIJKLMNOPQRSTUVWXYZ','abcdefghijklmnopqrstuvwxyz'),'loan')]"))
            );
            clickElement(loanLink);
            
            WebElement loanAmountField = wait.until(ExpectedConditions.presenceOfElementLocated(
                By.cssSelector("input[name*='amount' i], input[id*='amount' i]")
            ));
            loanAmountField.sendKeys("10000");
            
            WebElement purposeField = driver.findElement(
                By.cssSelector("input[name*='purpose' i], select[name*='purpose' i], textarea[name*='purpose' i]")
            );
            purposeField.sendKeys("Home Improvement");
            
            WebElement incomeField = driver.findElement(
                By.cssSelector("input[name*='income' i], input[id*='income' i]")
            );
            incomeField.sendKeys("40000");
            
            WebElement submitButton = driver.findElement(
                By.cssSelector("button[type='submit'], input[type='submit']")
            );
            clickElement(submitButton);
            
            WebElement confirmationMessage = wait.until(ExpectedConditions.presenceOfElementLocated(
                By.xpath("//*[contains(translate(.,'ABCDEFGHIJKLMNOPQRSTUVWXYZ','abcdefghijklmnopqrstuvwxyz'),'confirmation') or contains(translate(.,'ABCDEFGHIJKLMNOPQRSTUVWXYZ','abcdefghijklmnopqrstuvwxyz'),'submitted')]")
            ));
            
            Assert.assertTrue(confirmationMessage.isDisplayed(), "Loan application confirmation not displayed");
        } catch (Exception e) {
            Assert.fail("Loan application failed: " + e.getMessage());
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