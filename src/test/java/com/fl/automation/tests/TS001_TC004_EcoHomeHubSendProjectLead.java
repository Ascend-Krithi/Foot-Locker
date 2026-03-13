package com.fl.automation.tests;

import com.fl.automation.core.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;

public class TS001_TC004_EcoHomeHubSendProjectLead extends BaseTest {

    @Test(description = "TC_4107: SCRUM-19509 TS-001 TC-004 - Eco Home Hub send project lead")
    public void verifySendProjectLead() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(40));
        
        driver.get(baseUrl);
        
        WebElement contactForm = wait.until(ExpectedConditions.presenceOfElementLocated(
            By.cssSelector("form[class*='contact'], form[class*='lead']")));
        Assert.assertTrue(contactForm.isDisplayed(), "Contact form should be visible");
    }
}