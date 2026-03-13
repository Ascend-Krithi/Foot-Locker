package com.fl.automation.tests;

import org.testng.annotations.Test;
import org.testng.Assert;
import com.fl.automation.core.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class TS003_TC001_SCRUM19509 extends BaseTest {
    @Test
    public void testInstallerLogin() {
        driver.get("https://installer.eco-home-hub.example.com");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(40));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("username"))).sendKeys("installer");
        driver.findElement(By.id("password")).sendKeys("InstallerPassword123");
        driver.findElement(By.cssSelector("button[type='submit']")).click();
        boolean dashboardLoaded = wait.until(ExpectedConditions.urlContains("dashboard"));
        Assert.assertTrue(dashboardLoaded, "Installer should be logged in and redirected to dashboard");
    }
}