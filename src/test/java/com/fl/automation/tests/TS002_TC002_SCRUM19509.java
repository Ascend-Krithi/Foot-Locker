package com.fl.automation.tests;

import org.testng.annotations.Test;
import org.testng.Assert;
import com.fl.automation.core.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class TS002_TC002_SCRUM19509 extends BaseTest {
    @Test
    public void testAdminVetCertifications() {
        driver.get("https://admin.eco-home-hub.example.com");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(40));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("username"))).sendKeys("admin");
        driver.findElement(By.id("password")).sendKeys("AdminPassword123");
        driver.findElement(By.cssSelector("button[type='submit']")).click();
        wait.until(ExpectedConditions.urlContains("dashboard"));
        wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Installer Applications"))).click();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".application-list")));
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".application-card:first-child"))).click();
        boolean certificationsDisplayed = wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".certifications-list"))).isDisplayed();
        Assert.assertTrue(certificationsDisplayed, "Certifications should be displayed for review");
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(text(),'Validate Certifications')]"))).click();
        boolean certificationsValidated = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[contains(text(),'Certifications validated')]"))).isDisplayed();
        Assert.assertTrue(certificationsValidated, "Certifications should be validated");
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(text(),'Approve')]"))).click();
        boolean installerApproved = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[contains(text(),'Installer approved')]"))).isDisplayed();
        Assert.assertTrue(installerApproved, "Installer should be approved only if certifications are valid");
    }
}