package com.fl.automation.tests;

import org.testng.annotations.Test;
import org.testng.Assert;
import com.fl.automation.core.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class TS006_TC002_SCRUM19509 extends BaseTest {
    @Test
    public void testAnalyticsLoanApplicationsCompleted() {
        driver.get("https://admin.eco-home-hub.example.com");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(40));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("username"))).sendKeys("admin");
        driver.findElement(By.id("password")).sendKeys("AdminPassword123");
        driver.findElement(By.cssSelector("button[type='submit']")).click();
        wait.until(ExpectedConditions.urlContains("dashboard"));
        wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Analytics"))).click();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".analytics-dashboard")));
        driver.get("https://eco-home-hub.example.com");
        wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Apply for Loan"))).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("loanAmount"))).sendKeys("10000");
        driver.findElement(By.id("purpose")).sendKeys("Home Improvement");
        driver.findElement(By.id("income")).sendKeys("40000");
        driver.findElement(By.cssSelector("button[type='submit']")).click();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[contains(text(),'Loan application submitted')]")));
        driver.get("https://admin.eco-home-hub.example.com/analytics");
        boolean metricIncremented = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[contains(text(),'Loan applications completed')]"))).isDisplayed();
        Assert.assertTrue(metricIncremented, "Loan applications completed metric should increment");
    }
}