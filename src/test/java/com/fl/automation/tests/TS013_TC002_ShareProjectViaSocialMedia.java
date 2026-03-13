package com.fl.automation.tests;

import com.fl.automation.core.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;

public class TS013_TC002_ShareProjectViaSocialMedia extends BaseTest {

    @Test(description = "TC_4154: SCRUM-19509 TS-013 TC-002 - Share project via social media")
    public void verifyShareProjectViaSocialMedia() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(40));
        
        driver.get(baseUrl);
        
        WebElement shareButton = wait.until(ExpectedConditions.presenceOfElementLocated(
            By.cssSelector("button[class*='share'], a[class*='social']")));
        Assert.assertTrue(shareButton.isDisplayed(), "Share via social media functionality should be available");
    }
}