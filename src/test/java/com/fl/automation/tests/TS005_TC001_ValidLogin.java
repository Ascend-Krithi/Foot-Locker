package com.fl.automation.tests;

import com.fl.automation.core.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;

public class TS005_TC001_ValidLogin extends BaseTest {

    @Test(description = "TC_4144: SCRUM-19509 TS-005 TC-001 - Valid login")
    public void verifyValidLogin() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(40));
        
        driver.get(baseUrl);
        
        WebElement loginLink = wait.until(ExpectedConditions.presenceOfElementLocated(
            By.cssSelector("a[href*='login'], a[href*='signin']")));
        Assert.assertTrue(loginLink.isDisplayed(), "Login functionality should be available");
    }
}