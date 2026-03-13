package com.fl.automation.tests;

import org.testng.annotations.Test;
import org.testng.Assert;
import com.fl.automation.core.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class TS007_TC001_SCRUM19509 extends BaseTest {
    @Test
    public void testLoanApplicationMissingFields() {
        driver.get("https://eco-home-hub.example.com");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(40));
        wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Login"))).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("email"))).sendKeys("john.doe@email.com");
        driver.findElement(By.id("password")).sendKeys("StrongPassword123");
        driver.findElement(By.cssSelector("button[type='submit']")).click();
        wait.until(ExpectedConditions.urlContains("dashboard"));
        wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Apply for Loan"))).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("loanAmount")));
        driver.findElement(By.cssSelector("button[type='submit']")).click();
        boolean errorMessagesDisplayed = wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".error-message"))).isDisplayed();
        Assert.assertTrue(errorMessagesDisplayed, "Error messages should be displayed for missing fields");
    }
}