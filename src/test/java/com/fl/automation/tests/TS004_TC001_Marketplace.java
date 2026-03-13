package com.fl.automation.tests;

import org.testng.annotations.Test;
import org.testng.Assert;
import com.fl.automation.core.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class TS004_TC001_Marketplace extends BaseTest {
    @Test
    public void testContactOwner() {
        driver.get("https://eco-renovation.com/marketplace/project/12345");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(40));
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(text(),'Contact Owner')]"))).click();
        boolean contactFormDisplayed = wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".contact-form"))).isDisplayed();
        Assert.assertTrue(contactFormDisplayed, "Contact form should be displayed");
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("name"))).sendKeys("John Doe");
        driver.findElement(By.id("email")).sendKeys("john@example.com");
        driver.findElement(By.id("message")).sendKeys("Interested in project.");
        driver.findElement(By.cssSelector("button[type='submit']")).click();
        boolean confirmationDisplayed = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[contains(text(),'Message sent')]"))).isDisplayed();
        Assert.assertTrue(confirmationDisplayed, "Confirmation message should be displayed");
    }
}