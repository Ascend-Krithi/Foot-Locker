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
 * TC_ID: 4144
 * Test Case: Login With Valid Credentials
 * Description: Navigate to /marketplace/login, enter valid credentials, click login, verify user authenticated and redirected to dashboard.
 */
public class TS012_TC001_LoginWithValidCredentials extends BaseTest {

    @Test
    public void loginWithValidCredentials() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(40));
        
        driver.get("https://eco-renovation.com/marketplace/login");
        
        try {
            WebElement usernameField = wait.until(ExpectedConditions.presenceOfElementLocated(
                By.cssSelector("input[name='username'], input[id='username'], input[type='text']")
            ));
            usernameField.sendKeys("user1");
            
            WebElement passwordField = driver.findElement(
                By.cssSelector("input[type='password'], input[name='password']")
            );
            passwordField.sendKeys("Pass@123");
            
            WebElement loginButton = driver.findElement(
                By.cssSelector("button[type='submit'], input[type='submit'], button[class*='login' i]")
            );
            clickElement(loginButton);
            
            WebElement dashboard = wait.until(ExpectedConditions.presenceOfElementLocated(
                By.xpath("//*[contains(translate(.,'ABCDEFGHIJKLMNOPQRSTUVWXYZ','abcdefghijklmnopqrstuvwxyz'),'dashboard') or contains(@class,'dashboard')]")
            ));
            
            Assert.assertTrue(dashboard.isDisplayed(), "User not redirected to dashboard after login");
        } catch (Exception e) {
            Assert.fail("Login with valid credentials failed: " + e.getMessage());
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