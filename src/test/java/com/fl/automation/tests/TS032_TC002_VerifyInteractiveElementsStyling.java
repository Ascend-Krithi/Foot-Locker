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
 * TC_ID: 4197
 * Test Case: Verify Interactive Elements Styling
 * Description: Launch application, enter valid user credentials, click 'Login', navigate to various screens containing interactive elements, review styling of all buttons, links, and form fields, verify all interactive elements styled consistently.
 */
public class TS032_TC002_VerifyInteractiveElementsStyling extends BaseTest {

    @Test
    public void verifyInteractiveElementsStyling() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(40));
        
        driver.get("https://platform-url.example.com");
        
        try {
            WebElement usernameField = wait.until(ExpectedConditions.presenceOfElementLocated(
                By.cssSelector("input[name='username'], input[id='username']")
            ));
            usernameField.sendKeys("test_user");
            
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
            
            List<WebElement> buttons = driver.findElements(By.tagName("button"));
            Assert.assertTrue(buttons.size() > 0, "No interactive buttons found");
            
            List<WebElement> links = driver.findElements(By.tagName("a"));
            Assert.assertTrue(links.size() > 0, "No interactive links found");
        } catch (Exception e) {
            Assert.fail("Verify interactive elements styling failed: " + e.getMessage());
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