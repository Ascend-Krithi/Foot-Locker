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
 * TC_ID: 4149
 * Test Case: Edit User Profile
 * Description: Login, navigate to /marketplace/profile/edit, edit profile info, save changes, verify profile changes saved and reflected.
 */
public class TS016_TC001_EditUserProfile extends BaseTest {

    @Test
    public void editUserProfile() {
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
            
            driver.get("https://eco-renovation.com/marketplace/profile/edit");
            
            WebElement emailField = wait.until(ExpectedConditions.presenceOfElementLocated(
                By.cssSelector("input[type='email'], input[name*='email' i]")
            ));
            emailField.clear();
            emailField.sendKeys("user1_new@example.com");
            
            WebElement saveButton = driver.findElement(
                By.cssSelector("button[type='submit'], button[class*='save' i]")
            );
            clickElement(saveButton);
            
            WebElement successMessage = wait.until(ExpectedConditions.presenceOfElementLocated(
                By.xpath("//*[contains(translate(.,'ABCDEFGHIJKLMNOPQRSTUVWXYZ','abcdefghijklmnopqrstuvwxyz'),'saved') or contains(translate(.,'ABCDEFGHIJKLMNOPQRSTUVWXYZ','abcdefghijklmnopqrstuvwxyz'),'updated')]")
            ));
            
            Assert.assertTrue(successMessage.isDisplayed(), "Profile update confirmation not displayed");
        } catch (Exception e) {
            Assert.fail("Edit user profile failed: " + e.getMessage());
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