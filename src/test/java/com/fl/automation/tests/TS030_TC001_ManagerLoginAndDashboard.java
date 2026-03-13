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
 * TC_ID: 4189
 * Test Case: Manager Login And Dashboard
 * Description: Launch application, enter valid Manager credentials, click 'Login' button, observe Dashboard screen, identify and review presence and layout of all core modules on Dashboard, verify all core modules present, clearly labeled, and intuitively arranged.
 */
public class TS030_TC001_ManagerLoginAndDashboard extends BaseTest {

    @Test
    public void managerLoginAndDashboard() {
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
                By.cssSelector("button[type='submit'], input[type='submit']")
            );
            clickElement(loginButton);
            
            WebElement dashboard = wait.until(ExpectedConditions.presenceOfElementLocated(
                By.xpath("//*[contains(translate(.,'ABCDEFGHIJKLMNOPQRSTUVWXYZ','abcdefghijklmnopqrstuvwxyz'),'dashboard')]")
            ));
            Assert.assertTrue(dashboard.isDisplayed(), "Dashboard not displayed");
            
            List<WebElement> modules = driver.findElements(
                By.cssSelector("[class*='module'], [class*='widget'], [class*='card']")
            );
            Assert.assertTrue(modules.size() > 0, "No core modules found on dashboard");
        } catch (Exception e) {
            Assert.fail("Manager login and dashboard verification failed: " + e.getMessage());
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