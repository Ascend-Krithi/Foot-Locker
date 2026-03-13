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
 * TC_ID: 4111
 * Test Case: Installer Login
 * Description: Launch Installer Portal, enter valid credentials, click Login, verify dashboard loads.
 */
public class TS004_TC001_InstallerLogin extends BaseTest {

    @Test
    public void installerLogin() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(40));
        
        driver.get("https://installer.eco-home-hub.example.com");
        
        try {
            WebElement usernameField = wait.until(ExpectedConditions.presenceOfElementLocated(
                By.cssSelector("input[name='username'], input[id='username'], input[type='text']")
            ));
            usernameField.sendKeys("installer");
            
            WebElement passwordField = driver.findElement(
                By.cssSelector("input[type='password'], input[name='password']")
            );
            passwordField.sendKeys("InstallerPassword123");
            
            WebElement loginButton = driver.findElement(
                By.cssSelector("button[type='submit'], input[type='submit'], button[class*='login' i]")
            );
            clickElement(loginButton);
            
            WebElement dashboard = wait.until(ExpectedConditions.presenceOfElementLocated(
                By.xpath("//*[contains(translate(.,'ABCDEFGHIJKLMNOPQRSTUVWXYZ','abcdefghijklmnopqrstuvwxyz'),'dashboard') or contains(@class,'dashboard')]")
            ));
            
            Assert.assertTrue(dashboard.isDisplayed(), "Installer dashboard not loaded");
        } catch (Exception e) {
            Assert.fail("Installer login failed: " + e.getMessage());
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