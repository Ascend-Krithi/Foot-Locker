package com.fl.automation.tests;

import com.fl.automation.core.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;

public class TS001_TC001_EcoHomeHubCustomerRegistration extends BaseTest {

    @Test(description = "TC_4104: SCRUM-19509 TS-001 TC-001 - Eco Home Hub customer registration")
    public void verifyCustomerRegistration() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(40));
        
        driver.get(baseUrl);
        
        WebElement registerLink = wait.until(ExpectedConditions.presenceOfElementLocated(
            By.cssSelector("a[href*='register'], a[href*='signup']")));
        Assert.assertTrue(registerLink.isDisplayed(), "Register link should be visible");
    }
}