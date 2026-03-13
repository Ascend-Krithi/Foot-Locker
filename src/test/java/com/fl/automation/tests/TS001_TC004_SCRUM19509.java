package com.fl.automation.tests;

import org.testng.annotations.Test;
import org.testng.Assert;
import com.fl.automation.core.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class TS001_TC004_SCRUM19509 extends BaseTest {
    @Test
    public void testSendProjectLead() {
        driver.get("https://eco-home-hub.example.com");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(40));
        wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Register"))).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("name"))).sendKeys("John Doe");
        driver.findElement(By.id("email")).sendKeys("john.doe@email.com");
        driver.findElement(By.id("password")).sendKeys("StrongPassword123");
        driver.findElement(By.id("address")).sendKeys("123 Main St, London");
        driver.findElement(By.cssSelector("button[type='submit']")).click();
        wait.until(ExpectedConditions.urlContains("dashboard"));
        wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Apply for Loan"))).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("loanAmount"))).sendKeys("10000");
        driver.findElement(By.id("purpose")).sendKeys("Home Improvement");
        driver.findElement(By.id("income")).sendKeys("40000");
        driver.findElement(By.cssSelector("button[type='submit']")).click();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[contains(text(),'Loan application submitted')]")));
        wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Find Installer"))).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("location"))).sendKeys("London");
        driver.findElement(By.id("serviceType")).sendKeys("Solar Panel Installation");
        driver.findElement(By.cssSelector("button[type='submit']")).click();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".installer-list")));
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".installer-card:first-child"))).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(text(),'Send Project Lead')]"))).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("projectDescription"))).sendKeys("Install solar panels");
        driver.findElement(By.id("preferredDates")).sendKeys("2025-06-01 to 2025-06-15");
        driver.findElement(By.id("contactInfo")).sendKeys("john.doe@email.com");
        driver.findElement(By.cssSelector("button[type='submit']")).click();
        boolean confirmationDisplayed = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[contains(text(),'Project lead sent')]"))).isDisplayed();
        Assert.assertTrue(confirmationDisplayed, "Project lead should be sent successfully");
    }
}