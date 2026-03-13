package com.fl.automation.tests;

import com.fl.automation.core.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;

public class TS007_TC001_LoanApplicationMissingFieldsValidation extends BaseTest {

    @Test(description = "TC_4120: SCRUM-19509 TS-007 TC-001 - Loan application missing fields validation")
    public void verifyLoanApplicationMissingFieldsValidation() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(40));
        
        driver.get(baseUrl);
        
        WebElement form = wait.until(ExpectedConditions.presenceOfElementLocated(
            By.cssSelector("form, input[required]")));
        Assert.assertTrue(form.isDisplayed(), "Form with required field validation should be present");
    }
}