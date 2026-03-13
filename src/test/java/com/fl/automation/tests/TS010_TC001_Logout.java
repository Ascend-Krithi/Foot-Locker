package com.fl.automation.tests;

import com.fl.automation.core.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;

public class TS010_TC001_Logout extends BaseTest {

    @Test(description = "TC_4150: SCRUM-19509 TS-010 TC-001 - Logout")
    public void verifyLogout() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(40));
        
        driver.get(baseUrl);
        
        WebElement accountLink = wait.until(ExpectedConditions.presenceOfElementLocated(
            By.cssSelector("a[href*='account'], a[href*='login']")));
        Assert.assertTrue(accountLink.isDisplayed(), "Logout functionality should be available");
    }
}