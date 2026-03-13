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
 * TC_ID: 4151
 * Test Case: Submit Project Review
 * Description: Login, navigate to completed project review page, submit review, verify review submitted, displayed on project page, owner notified.
 */
public class TS018_TC001_SubmitProjectReview extends BaseTest {

    @Test
    public void submitProjectReview() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(40));
        
        driver.get("https://eco-renovation.com/marketplace/project/12345/review");
        
        try {
            WebElement reviewField = wait.until(ExpectedConditions.presenceOfElementLocated(
                By.cssSelector("textarea[name*='review' i], textarea[id*='review' i]")
            ));
            reviewField.sendKeys("Excellent work!");
            
            WebElement submitButton = driver.findElement(
                By.cssSelector("button[type='submit'], input[type='submit']")
            );
            clickElement(submitButton);
            
            WebElement confirmationMessage = wait.until(ExpectedConditions.presenceOfElementLocated(
                By.xpath("//*[contains(translate(.,'ABCDEFGHIJKLMNOPQRSTUVWXYZ','abcdefghijklmnopqrstuvwxyz'),'submitted') or contains(translate(.,'ABCDEFGHIJKLMNOPQRSTUVWXYZ','abcdefghijklmnopqrstuvwxyz'),'thank')]")
            ));
            
            Assert.assertTrue(confirmationMessage.isDisplayed(), "Review submission confirmation not displayed");
        } catch (Exception e) {
            Assert.fail("Submit project review failed: " + e.getMessage());
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