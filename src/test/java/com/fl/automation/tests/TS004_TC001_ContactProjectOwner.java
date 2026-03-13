package com.fl.automation.tests;

import com.fl.automation.core.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;

public class TS004_TC001_ContactProjectOwner extends BaseTest {

    @Test(description = "TC_4143: SCRUM-19509 TS-004 TC-001 - Contact project owner")
    public void verifyContactProjectOwner() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(40));
        
        driver.get(baseUrl);
        
        WebElement contactForm = wait.until(ExpectedConditions.presenceOfElementLocated(
            By.cssSelector("form, a[href*='contact'], button[class*='contact']")));
        Assert.assertTrue(contactForm.isDisplayed(), "Contact project owner functionality should be available");
    }
}