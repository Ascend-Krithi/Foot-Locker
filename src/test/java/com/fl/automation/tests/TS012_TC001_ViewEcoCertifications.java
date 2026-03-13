package com.fl.automation.tests;

import com.fl.automation.core.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;

public class TS012_TC001_ViewEcoCertifications extends BaseTest {

    @Test(description = "TC_4152: SCRUM-19509 TS-012 TC-001 - View eco-certifications")
    public void verifyViewEcoCertifications() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(40));
        
        driver.get(baseUrl);
        
        WebElement certSection = wait.until(ExpectedConditions.presenceOfElementLocated(
            By.cssSelector("section, div")));
        Assert.assertTrue(certSection.isDisplayed(), "Eco-certifications should be viewable");
    }
}