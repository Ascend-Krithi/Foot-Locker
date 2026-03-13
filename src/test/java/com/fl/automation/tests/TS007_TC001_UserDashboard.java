package com.fl.automation.tests;

import com.fl.automation.core.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;

public class TS007_TC001_UserDashboard extends BaseTest {

    @Test(description = "TC_4147: SCRUM-19509 TS-007 TC-001 - User dashboard")
    public void verifyUserDashboard() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(40));
        
        driver.get(baseUrl);
        
        WebElement accountLink = wait.until(ExpectedConditions.presenceOfElementLocated(
            By.cssSelector("a[href*='account'], a[href*='dashboard']")));
        Assert.assertTrue(accountLink.isDisplayed(), "User dashboard should be accessible");
    }
}