package com.fl.automation.tests;

import com.fl.automation.core.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;

/**
 * TC_ID: 4156
 * Test Case: Access Help And Support
 * Description: Navigate to /marketplace/help from any marketplace page, verify help and support resources accessible and functional.
 */
public class TS022_TC001_AccessHelpAndSupport extends BaseTest {

    @Test
    public void accessHelpAndSupport() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(40));
        
        driver.get("https://eco-renovation.com/marketplace/help");
        
        try {
            WebElement helpContent = wait.until(ExpectedConditions.presenceOfElementLocated(
                By.xpath("//*[contains(translate(.,'ABCDEFGHIJKLMNOPQRSTUVWXYZ','abcdefghijklmnopqrstuvwxyz'),'help') or contains(translate(.,'ABCDEFGHIJKLMNOPQRSTUVWXYZ','abcdefghijklmnopqrstuvwxyz'),'support') or contains(translate(.,'ABCDEFGHIJKLMNOPQRSTUVWXYZ','abcdefghijklmnopqrstuvwxyz'),'faq')]")
            ));
            
            Assert.assertTrue(helpContent.isDisplayed(), "Help and support resources not accessible");
        } catch (Exception e) {
            Assert.fail("Access help and support failed: " + e.getMessage());
        }
    }
}