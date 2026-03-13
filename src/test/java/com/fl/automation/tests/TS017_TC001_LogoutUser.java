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
 * TC_ID: 4150
 * Test Case: Logout User
 * Description: Login, navigate to /marketplace/logout, verify user logged out, session terminated, redirected to home.
 */
public class TS017_TC001_LogoutUser extends BaseTest {

    @Test
    public void logoutUser() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(40));
        
        driver.get("https://eco-renovation.com/marketplace/login");
        
        try {
            WebElement usernameField = wait.until(ExpectedConditions.presenceOfElementLocated(
                By.cssSelector("input[name='username'], input[id='username']")
            ));
            usernameField.sendKeys("user1");
            
            WebElement passwordField = driver.findElement(
                By.cssSelector("input[type='password']")
            );
            passwordField.sendKeys("Pass@123");
            
            WebElement loginButton = driver.findElement(
                By.cssSelector("button[type='submit']")
            );
            clickElement(loginButton);
            
            driver.get("https://eco-renovation.com/marketplace/logout");
            
            WebElement loginPage = wait.until(ExpectedConditions.presenceOfElementLocated(
                By.cssSelector("input[name='username'], input[id='username'], form[class*='login' i]")
            ));
            
            Assert.assertTrue(loginPage.isDisplayed(), "User not redirected to login/home page after logout");
        } catch (Exception e) {
            Assert.fail("Logout user failed: " + e.getMessage());
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