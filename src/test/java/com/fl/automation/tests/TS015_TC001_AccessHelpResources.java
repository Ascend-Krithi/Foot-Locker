package com.fl.automation.tests;

import com.fl.automation.core.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;

public class TS015_TC001_AccessHelpResources extends BaseTest {

    @Test(description = "TC_4156: SCRUM-19509 TS-015 TC-001 - Access help resources")
    public void verifyAccessHelpResources() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(40));
        
        driver.get(baseUrl);
        
        WebElement helpLink = wait.until(ExpectedConditions.presenceOfElementLocated(
            By.cssSelector("a[href*='help'], a[href*='support']")));
        Assert.assertTrue(helpLink.isDisplayed(), "Help resources should be accessible");
    }
}