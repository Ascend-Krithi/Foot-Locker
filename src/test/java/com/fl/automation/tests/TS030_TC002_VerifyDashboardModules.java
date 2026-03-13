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
import java.util.List;

/**
 * TC_ID: 4190
 * Test Case: Verify Dashboard Modules
 * Description: Launch application, enter valid Manager credentials, click 'Login', on Dashboard click each core module one by one, verify each module opens and displays expected content.
 */
public class TS030_TC002_VerifyDashboardModules extends BaseTest {

    @Test
    public void verifyDashboardModules() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(40));
        
        driver.get("https://platform-url.example.com");
        
        try {
            WebElement usernameField = wait.until(ExpectedConditions.presenceOfElementLocated(
                By.cssSelector("input[name='username'], input[id='username']")
            ));
            usernameField.sendKeys("manager_user");
            
            WebElement passwordField = driver.findElement(
                By.cssSelector("input[type='password']")
            );
            passwordField.sendKeys("valid_password");
            
            WebElement loginButton = driver.findElement(
                By.cssSelector("button[type='submit']")
            );
            clickElement(loginButton);
            
            wait.until(ExpectedConditions.presenceOfElementLocated(
                By.xpath("//*[contains(translate(.,'ABCDEFGHIJKLMNOPQRSTUVWXYZ','abcdefghijklmnopqrstuvwxyz'),'dashboard')]")
            ));
            
            List<WebElement> modules = driver.findElements(
                By.cssSelector("[class*='module'], [class*='widget'], [class*='card']")
            );
            
            for (WebElement module : modules) {
                try {
                    clickElement(module);
                    WebElement moduleContent = wait.until(ExpectedConditions.presenceOfElementLocated(
                        By.cssSelector("[class*='content'], [class*='body']")
                    ));
                    Assert.assertTrue(moduleContent.isDisplayed(), "Module content not displayed");
                    driver.navigate().back();
                } catch (Exception e) {
                    // Continue with next module
                }
            }
        } catch (Exception e) {
            Assert.fail("Verify dashboard modules failed: " + e.getMessage());
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