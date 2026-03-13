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
 * TC_ID: 4152
 * Test Case: View Eco Certification Details
 * Description: Navigate to project details, click eco-certification section, verify eco-certification details displayed accurately.
 */
public class TS019_TC001_ViewEcoCertificationDetails extends BaseTest {

    @Test
    public void viewEcoCertificationDetails() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(40));
        
        driver.get("https://eco-renovation.com/marketplace/project/12345");
        
        try {
            WebElement ecoCertSection = wait.until(ExpectedConditions.presenceOfElementLocated(
                By.xpath("//*[contains(translate(.,'ABCDEFGHIJKLMNOPQRSTUVWXYZ','abcdefghijklmnopqrstuvwxyz'),'eco') or contains(translate(.,'ABCDEFGHIJKLMNOPQRSTUVWXYZ','abcdefghijklmnopqrstuvwxyz'),'certification')]")
            ));
            clickElement(ecoCertSection);
            
            WebElement certificationDetails = wait.until(ExpectedConditions.presenceOfElementLocated(
                By.xpath("//*[contains(@class,'certification') or contains(@class,'eco')]")
            ));
            
            Assert.assertTrue(certificationDetails.isDisplayed(), "Eco-certification details not displayed");
        } catch (Exception e) {
            Assert.fail("View eco certification details failed: " + e.getMessage());
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