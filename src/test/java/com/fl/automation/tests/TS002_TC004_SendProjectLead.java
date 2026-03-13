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
 * TC_ID: 4107
 * Test Case: Send Project Lead
 * Description: Register, login, complete loan application, search installer, select installer, click Send Project Lead, fill details, submit, verify confirmation.
 */
public class TS002_TC004_SendProjectLead extends BaseTest {

    @Test
    public void sendProjectLead() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(40));
        
        driver.get("https://eco-home-hub.example.com");
        
        try {
            WebElement sendLeadButton = wait.until(ExpectedConditions.presenceOfElementLocated(
                By.xpath("//button[contains(translate(.,'ABCDEFGHIJKLMNOPQRSTUVWXYZ','abcdefghijklmnopqrstuvwxyz'),'send') and contains(translate(.,'ABCDEFGHIJKLMNOPQRSTUVWXYZ','abcdefghijklmnopqrstuvwxyz'),'lead')]"))
            );
            clickElement(sendLeadButton);
            
            WebElement descriptionField = wait.until(ExpectedConditions.presenceOfElementLocated(
                By.cssSelector("textarea[name*='description' i], textarea[id*='description' i]")
            ));
            descriptionField.sendKeys("Install solar panels");
            
            WebElement datesField = driver.findElement(
                By.cssSelector("input[name*='date' i], input[type='date']")
            );
            datesField.sendKeys("2025-06-01");
            
            WebElement contactField = driver.findElement(
                By.cssSelector("input[type='email'], input[name*='contact' i]")
            );
            contactField.sendKeys("john.doe@email.com");
            
            WebElement submitButton = driver.findElement(
                By.cssSelector("button[type='submit'], input[type='submit']")
            );
            clickElement(submitButton);
            
            WebElement confirmationMessage = wait.until(ExpectedConditions.presenceOfElementLocated(
                By.xpath("//*[contains(translate(.,'ABCDEFGHIJKLMNOPQRSTUVWXYZ','abcdefghijklmnopqrstuvwxyz'),'confirmation') or contains(translate(.,'ABCDEFGHIJKLMNOPQRSTUVWXYZ','abcdefghijklmnopqrstuvwxyz'),'sent')]")
            ));
            
            Assert.assertTrue(confirmationMessage.isDisplayed(), "Project lead confirmation not displayed");
        } catch (Exception e) {
            Assert.fail("Send project lead failed: " + e.getMessage());
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