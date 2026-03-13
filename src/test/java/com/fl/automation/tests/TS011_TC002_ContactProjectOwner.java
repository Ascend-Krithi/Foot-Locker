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
 * TC_ID: 4143
 * Test Case: Contact Project Owner
 * Description: On project details page, click 'Contact Owner', fill contact form, submit, verify confirmation message and owner receives notification.
 */
public class TS011_TC002_ContactProjectOwner extends BaseTest {

    @Test
    public void contactProjectOwner() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(40));
        
        driver.get("https://eco-renovation.com/marketplace/project/12345");
        
        try {
            WebElement contactButton = wait.until(ExpectedConditions.presenceOfElementLocated(
                By.xpath("//button[contains(translate(.,'ABCDEFGHIJKLMNOPQRSTUVWXYZ','abcdefghijklmnopqrstuvwxyz'),'contact') and contains(translate(.,'ABCDEFGHIJKLMNOPQRSTUVWXYZ','abcdefghijklmnopqrstuvwxyz'),'owner')]")
            ));
            clickElement(contactButton);
            
            WebElement nameField = wait.until(ExpectedConditions.presenceOfElementLocated(
                By.cssSelector("input[name*='name' i], input[id*='name' i]")
            ));
            nameField.sendKeys("John Doe");
            
            WebElement emailField = driver.findElement(
                By.cssSelector("input[type='email'], input[name*='email' i]")
            );
            emailField.sendKeys("john@example.com");
            
            WebElement messageField = driver.findElement(
                By.cssSelector("textarea[name*='message' i], textarea[id*='message' i]")
            );
            messageField.sendKeys("Interested in project");
            
            WebElement submitButton = driver.findElement(
                By.cssSelector("button[type='submit'], input[type='submit']")
            );
            clickElement(submitButton);
            
            WebElement confirmationMessage = wait.until(ExpectedConditions.presenceOfElementLocated(
                By.xpath("//*[contains(translate(.,'ABCDEFGHIJKLMNOPQRSTUVWXYZ','abcdefghijklmnopqrstuvwxyz'),'confirmation') or contains(translate(.,'ABCDEFGHIJKLMNOPQRSTUVWXYZ','abcdefghijklmnopqrstuvwxyz'),'sent')]")
            ));
            
            Assert.assertTrue(confirmationMessage.isDisplayed(), "Contact confirmation message not displayed");
        } catch (Exception e) {
            Assert.fail("Contact project owner failed: " + e.getMessage());
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