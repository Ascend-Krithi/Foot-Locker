package com.fl.automation.tests;

import org.testng.annotations.Test;
import org.testng.Assert;
import com.fl.automation.core.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class TS003_TC002_SCRUM19509 extends BaseTest {
    @Test
    public void testInstallerViewProjectLeads() {
        driver.get("https://installer.eco-home-hub.example.com");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(40));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("username"))).sendKeys("installer");
        driver.findElement(By.id("password")).sendKeys("InstallerPassword123");
        driver.findElement(By.cssSelector("button[type='submit']")).click();
        wait.until(ExpectedConditions.urlContains("dashboard"));
        wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Project Leads"))).click();
        boolean projectLeadsDisplayed = wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".project-leads-list"))).isDisplayed();
        Assert.assertTrue(projectLeadsDisplayed, "Project leads should be displayed");
        boolean leadDetailsVisible = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[contains(text(),'Install solar panels')]"))).isDisplayed();
        Assert.assertTrue(leadDetailsVisible, "Project lead details should be visible with accurate information");
    }
}