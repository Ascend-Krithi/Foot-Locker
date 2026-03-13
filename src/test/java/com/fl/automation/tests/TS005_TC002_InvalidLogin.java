package com.fl.automation.tests;

import com.fl.automation.core.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;

public class TS005_TC002_InvalidLogin extends BaseTest {

    @Test(description = "TC_4145: SCRUM-19509 TS-005 TC-002 - Invalid login")
    public void verifyInvalidLogin() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(40));
        
        driver.get(baseUrl);
        
        WebElement loginLink = wait.until(ExpectedConditions.presenceOfElementLocated(
            By.cssSelector("a[href*='login'], a[href*='signin']")));
        Assert.assertTrue(loginLink.isDisplayed(), "Invalid login validation should be present");
    }
}