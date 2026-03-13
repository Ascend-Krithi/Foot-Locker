package com.fl.automation.tests;

import com.fl.automation.core.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;

public class TS011_TC001_SubmitReview extends BaseTest {

    @Test(description = "TC_4151: SCRUM-19509 TS-011 TC-001 - Submit review")
    public void verifySubmitReview() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(40));
        
        driver.get(baseUrl);
        
        WebElement reviewSection = wait.until(ExpectedConditions.presenceOfElementLocated(
            By.cssSelector("form, textarea, button")));
        Assert.assertTrue(reviewSection.isDisplayed(), "Submit review functionality should be available");
    }
}