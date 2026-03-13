package com.fl.automation.tests;

import com.fl.automation.core.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;

/**
 * TC_ID: 4123
 * Test Case: Onboard Installer With Maximum Certifications
 * Description: Login as admin, navigate to installer applications, select application with maximum certifications, review/vet all, approve, verify installer onboarded.
 */
public class TS009_TC002_OnboardInstallerWithMaximumCertifications extends BaseTest {

    @Test
    public void onboardInstallerWithMaximumCertifications() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(40));
        
        driver.get("https://admin.eco-home-hub.example.com");
        
        try {
            WebElement usernameField = wait.until(ExpectedConditions.presenceOfElementLocated(
                By.cssSelector("input[name='username'], input[id='username']")
            ));
            usernameField.sendKeys("admin");
            
            WebElement passwordField = driver.findElement(
                By.cssSelector("input[type='password']")
            );
            passwordField.sendKeys("AdminPassword123");
            
            WebElement loginButton = driver.findElement(
                By.cssSelector("button[type='submit']")
            );
            clickElement(loginButton);
            
            WebElement installerApplicationsLink = wait.until(ExpectedConditions.presenceOfElementLocated(
                By.xpath("//a[contains(translate(.,'ABCDEFGHIJKLMNOPQRSTUVWXYZ','abcdefghijklmnopqrstuvwxyz'),'installer')]")
            ));
            clickElement(installerApplicationsLink);
            
            WebElement maxCertApplication = wait.until(ExpectedConditions.presenceOfElementLocated(
                By.xpath("//*[contains(translate(.,'ABCDEFGHIJKLMNOPQRSTUVWXYZ','abcdefghijklmnopqrstuvwxyz'),'mcs') and contains(translate(.,'ABCDEFGHIJKLMNOPQRSTUVWXYZ','abcdefghijklmnopqrstuvwxyz'),'niceic')]")
            ));
            clickElement(maxCertApplication);
            
            WebElement approveButton = wait.until(ExpectedConditions.presenceOfElementLocated(
                By.xpath("//button[contains(translate(.,'ABCDEFGHIJKLMNOPQRSTUVWXYZ','abcdefghijklmnopqrstuvwxyz'),'approve')]")
            ));
            clickElement(approveButton);
            
            WebElement successMessage = wait.until(ExpectedConditions.presenceOfElementLocated(
                By.xpath("//*[contains(translate(.,'ABCDEFGHIJKLMNOPQRSTUVWXYZ','abcdefghijklmnopqrstuvwxyz'),'approved') or contains(translate(.,'ABCDEFGHIJKLMNOPQRSTUVWXYZ','abcdefghijklmnopqrstuvwxyz'),'onboarded')]")
            ));
            
            Assert.assertTrue(successMessage.isDisplayed(), "Installer with maximum certifications not onboarded");
        } catch (Exception e) {
            Assert.fail("Onboard installer with maximum certifications failed: " + e.getMessage());
        }
    }
    
    private void clickElement(WebElement element) {
        try {
            element.click();
        } catch (Exception e) {
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("arguments[0].click();", element);
        }
    }
}