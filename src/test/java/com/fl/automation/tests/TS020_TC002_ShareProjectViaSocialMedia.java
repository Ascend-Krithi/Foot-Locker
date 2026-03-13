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
 * TC_ID: 4154
 * Test Case: Share Project Via Social Media
 * Description: Navigate to project details, click 'Share', select social media option, share project details, verify project shared successfully and confirmation displayed.
 */
public class TS020_TC002_ShareProjectViaSocialMedia extends BaseTest {

    @Test
    public void shareProjectViaSocialMedia() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(40));
        
        driver.get("https://eco-renovation.com/marketplace/project/12345");
        
        try {
            WebElement shareButton = wait.until(ExpectedConditions.presenceOfElementLocated(
                By.xpath("//button[contains(translate(.,'ABCDEFGHIJKLMNOPQRSTUVWXYZ','abcdefghijklmnopqrstuvwxyz'),'share')]")
            ));
            clickElement(shareButton);
            
            WebElement twitterOption = wait.until(ExpectedConditions.presenceOfElementLocated(
                By.xpath("//*[contains(translate(.,'ABCDEFGHIJKLMNOPQRSTUVWXYZ','abcdefghijklmnopqrstuvwxyz'),'twitter') or contains(translate(.,'ABCDEFGHIJKLMNOPQRSTUVWXYZ','abcdefghijklmnopqrstuvwxyz'),'social')]")
            ));
            clickElement(twitterOption);
            
            // Handle potential popup window
            String mainWindow = driver.getWindowHandle();
            for (String windowHandle : driver.getWindowHandles()) {
                if (!windowHandle.equals(mainWindow)) {
                    driver.switchTo().window(windowHandle);
                    break;
                }
            }
            
            // Verify share action initiated (URL contains twitter or social media platform)
            String currentUrl = driver.getCurrentUrl();
            boolean isShareInitiated = currentUrl.contains("twitter") || currentUrl.contains("share");
            
            Assert.assertTrue(isShareInitiated, "Share via social media not initiated");
            
            driver.switchTo().window(mainWindow);
        } catch (Exception e) {
            Assert.fail("Share project via social media failed: " + e.getMessage());
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