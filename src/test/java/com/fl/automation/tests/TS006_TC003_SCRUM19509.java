package com.fl.automation.tests;

import org.testng.annotations.Test;
import org.testng.Assert;
import com.fl.automation.core.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class TS006_TC003_SCRUM19509 extends BaseTest {
    @Test
    public void testAnalyticsLeadsSent() {
        driver.get("https://admin.eco-home-hub.example.com");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(40));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("username"))).sendKeys("admin");
        driver.findElement(By.id("password")).sendKeys("AdminPassword123");
        driver.findElement(By.cssSelector("button[type='submit']")).click();
        wait.until(ExpectedConditions.urlContains("dashboard"));
        wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Analytics"))).click();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".analytics-dashboard")));
        driver.get("https://eco-home-hub.example.com");
        wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Find Installer"))).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("location"))).sendKeys("London");
        driver.findElement(By.id("serviceType")).sendKeys("Solar Panel Installation");
        driver.findElement(By.cssSelector("button[type='submit']")).click();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".installer-list")));
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".installer-card:first-child"))).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(text(),'Send Project Lead')]"))).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("projectDescription"))).sendKeys("Install solar panels");
        driver.findElement(By.cssSelector("button[type='submit']")).click();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[contains(text(),'Project lead sent')]")));
        driver.get("https://admin.eco-home-hub.example.com/analytics");
        boolean metricIncremented = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[contains(text(),'Leads sent to installers')]"))).isDisplayed();
        Assert.assertTrue(metricIncremented, "Leads sent to installers metric should increment");
    }
}