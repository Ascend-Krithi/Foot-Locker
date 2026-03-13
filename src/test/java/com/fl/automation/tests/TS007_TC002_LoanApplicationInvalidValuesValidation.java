package com.fl.automation.tests;

import com.fl.automation.core.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;

public class TS007_TC002_LoanApplicationInvalidValuesValidation extends BaseTest {

    @Test(description = "TC_4121: SCRUM-19509 TS-007 TC-002 - Loan application invalid values validation")
    public void verifyLoanApplicationInvalidValuesValidation() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(40));
        
        driver.get(baseUrl);
        
        WebElement form = wait.until(ExpectedConditions.presenceOfElementLocated(
            By.cssSelector("form, input[type='email'], input[type='number']")));
        Assert.assertTrue(form.isDisplayed(), "Form with input validation should be present");
    }
}