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
 * TC_ID: 4108
 * Test Case: Admin Approve Installer
 * Description: Launch Admin Dashboard, login as admin, navigate to installer applications, select pending application, vet certifications, approve, verify installer visible in marketplace.
 */
public class TS003_TC001_AdminApproveInstaller extends BaseTest {

    @Test
    public void adminApproveInstaller() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(40));
        
        driver.get("https://admin.eco-home-hub.example.com");
        
        try {
            WebElement usernameField = wait.until(ExpectedConditions.presenceOfElementLocated(
                By.cssSelector("input[name='username'], input[id='username']")
            ));
            usernameField.sendKeys("admin");
            
            WebElement passwordField = driver.findElement(
                By.cssSelector("input[type='password'], input[name='password']")
            );
            passwordField.sendKeys("AdminPassword123");
            
            WebElement loginButton = driver.findElement(
                By.cssSelector("button[type='submit'], input[type='submit']")
            );
            clickElement(loginButton);
            
            WebElement installerApplicationsLink = wait.until(ExpectedConditions.presenceOfElementLocated(
                By.xpath("//a[contains(translate(.,'ABCDEFGHIJKLMNOPQRSTUVWXYZ','abcdefghijklmnopqrstuvwxyz'),'installer') and contains(translate(.,'ABCDEFGHIJKLMNOPQRSTUVWXYZ','abcdefghijklmnopqrstuvwxyz'),'application')]")
            ));
            clickElement(installerApplicationsLink);
            
            WebElement pendingApplication = wait.until(ExpectedConditions.presenceOfElementLocated(
                By.xpath("//*[contains(text(),'Green Installers Ltd') or contains(text(),'Pending')]")
            ));
            clickElement(pendingApplication);
            
            WebElement approveButton = wait.until(ExpectedConditions.presenceOfElementLocated(
                By.xpath("//button[contains(translate(.,'ABCDEFGHIJKLMNOPQRSTUVWXYZ','abcdefghijklmnopqrstuvwxyz'),'approve')]")
            ));
            clickElement(approveButton);
            
            WebElement successMessage = wait.until(ExpectedConditions.presenceOfElementLocated(
                By.xpath("//*[contains(translate(.,'ABCDEFGHIJKLMNOPQRSTUVWXYZ','abcdefghijklmnopqrstuvwxyz'),'approved') or contains(translate(.,'ABCDEFGHIJKLMNOPQRSTUVWXYZ','abcdefghijklmnopqrstuvwxyz'),'success')]")
            ));
            
            Assert.assertTrue(successMessage.isDisplayed(), "Installer approval confirmation not displayed");
        } catch (Exception e) {
            Assert.fail("Admin approve installer failed: " + e.getMessage());
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