package com.fl.automation.tests;

import org.testng.annotations.Test;
import org.testng.Assert;
import com.fl.automation.core.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class TS001_TC002_SCRUM19509 extends BaseTest {
    @Test
    public void testLoanApplication() {
        driver.get("https://eco-home-hub.example.com");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(40));
        WebElement registerLink = wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Register")));
        registerLink.click();
        WebElement nameField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("name")));
        nameField.sendKeys("John Doe");
        driver.findElement(By.id("email")).sendKeys("john.doe@email.com");
        driver.findElement(By.id("password")).sendKeys("StrongPassword123");
        driver.findElement(By.id("address")).sendKeys("123 Main St, London");
        driver.findElement(By.cssSelector("button[type='submit']")).click();
        wait.until(ExpectedConditions.urlContains("dashboard"));
        WebElement loanLink = wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Apply for Loan")));
        loanLink.click();
        WebElement loanAmountField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("loanAmount")));
        loanAmountField.sendKeys("10000");
        driver.findElement(By.id("purpose")).sendKeys("Home Improvement");
        driver.findElement(By.id("income")).sendKeys("40000");
        driver.findElement(By.cssSelector("button[type='submit']")).click();
        boolean confirmationDisplayed = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[contains(text(),'Loan application submitted')]"))).isDisplayed();
        Assert.assertTrue(confirmationDisplayed, "Loan application should be submitted successfully");
    }
}