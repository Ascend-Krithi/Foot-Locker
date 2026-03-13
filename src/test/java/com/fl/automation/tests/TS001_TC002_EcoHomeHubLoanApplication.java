package com.fl.automation.tests;

import com.fl.automation.core.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;

public class TS001_TC002_EcoHomeHubLoanApplication extends BaseTest {

    @Test(description = "TC_4105: SCRUM-19509 TS-001 TC-002 - Eco Home Hub loan application")
    public void verifyLoanApplication() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(40));
        
        driver.get(baseUrl);
        
        WebElement loanSection = wait.until(ExpectedConditions.presenceOfElementLocated(
            By.cssSelector("section[class*='loan'], div[class*='financing']")));
        Assert.assertTrue(loanSection.isDisplayed(), "Loan application section should be visible");
    }
}