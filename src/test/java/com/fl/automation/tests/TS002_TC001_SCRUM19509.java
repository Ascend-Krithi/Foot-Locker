package com.fl.automation.tests;

import org.testng.annotations.Test;
import org.testng.Assert;
import com.fl.automation.core.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class TS002_TC001_SCRUM19509 extends BaseTest {
    @Test
    public void testAdminApproveInstaller() {
        driver.get("https://admin.eco-home-hub.example.com");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(40));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("username"))).sendKeys("admin");
        driver.findElement(By.id("password")).sendKeys("AdminPassword123");
        driver.findElement(By.cssSelector("button[type='submit']")).click();
        wait.until(ExpectedConditions.urlContains("dashboard"));
        wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Installer Applications"))).click();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".application-list")));
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".application-card:first-child"))).click();
        boolean certificationFormDisplayed = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("certifications"))).isDisplayed();
        Assert.assertTrue(certificationFormDisplayed, "Certification vetting form should be displayed");
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(text(),'Approve')]"))).click();
        boolean installerApproved = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[contains(text(),'Installer approved')]"))).isDisplayed();
        Assert.assertTrue(installerApproved, "Installer should be approved");
    }
}