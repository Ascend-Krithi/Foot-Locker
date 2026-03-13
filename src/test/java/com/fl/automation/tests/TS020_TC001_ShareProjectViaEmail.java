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
 * TC_ID: 4153
 * Test Case: Share Project Via Email
 * Description: Navigate to project details, click 'Share', select email option, send project details, verify project shared successfully and confirmation displayed.
 */
public class TS020_TC001_ShareProjectViaEmail extends BaseTest {

    @Test
    public void shareProjectViaEmail() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(40));
        
        driver.get("https://eco-renovation.com/marketplace/project/12345");
        
        try {
            WebElement shareButton = wait.until(ExpectedConditions.presenceOfElementLocated(
                By.xpath("//button[contains(translate(.,'ABCDEFGHIJKLMNOPQRSTUVWXYZ','abcdefghijklmnopqrstuvwxyz'),'share')]")
            ));
            clickElement(shareButton);
            
            WebElement emailOption = wait.until(ExpectedConditions.presenceOfElementLocated(
                By.xpath("//*[contains(translate(.,'ABCDEFGHIJKLMNOPQRSTUVWXYZ','abcdefghijklmnopqrstuvwxyz'),'email')]")
            ));
            clickElement(emailOption);
            
            WebElement recipientField = wait.until(ExpectedConditions.presenceOfElementLocated(
                By.cssSelector("input[type='email'], input[name*='recipient' i]")
            ));
            recipientField.sendKeys("friend@example.com");
            
            WebElement sendButton = driver.findElement(
                By.cssSelector("button[type='submit'], button[class*='send' i]")
            );
            clickElement(sendButton);
            
            WebElement confirmationMessage = wait.until(ExpectedConditions.presenceOfElementLocated(
                By.xpath("//*[contains(translate(.,'ABCDEFGHIJKLMNOPQRSTUVWXYZ','abcdefghijklmnopqrstuvwxyz'),'shared') or contains(translate(.,'ABCDEFGHIJKLMNOPQRSTUVWXYZ','abcdefghijklmnopqrstuvwxyz'),'sent')]")
            ));
            
            Assert.assertTrue(confirmationMessage.isDisplayed(), "Share via email confirmation not displayed");
        } catch (Exception e) {
            Assert.fail("Share project via email failed: " + e.getMessage());
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