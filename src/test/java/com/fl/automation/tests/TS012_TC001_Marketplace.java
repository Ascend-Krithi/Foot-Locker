package com.fl.automation.tests;

import org.testng.annotations.Test;
import org.testng.Assert;
import com.fl.automation.core.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class TS012_TC001_Marketplace extends BaseTest {
    @Test
    public void testMarketplaceEcoCertificationDetails() {
        driver.get("https://eco-renovation.com/marketplace/project/12345");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(40));
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".eco-certifications"))).click();
        boolean certificationDetailsDisplayed = wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".certification-details"))).isDisplayed();
        Assert.assertTrue(certificationDetailsDisplayed, "Eco-certification details should be displayed accurately");
    }
}