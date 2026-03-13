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
 * TC_ID: 4194
 * Test Case: Submit Incident Report
 * Description: Launch application, enter valid Staff credentials, click 'Login', navigate to Incident Reporting Form, fill in all required fields with valid data, click 'Submit' button, verify form submitted successfully and confirmation message displayed.
 */
public class TS031_TC003_SubmitIncidentReport extends BaseTest {

    @Test
    public void submitIncidentReport() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(40));
        
        driver.get("https://platform-url.example.com");
        
        try {
            WebElement usernameField = wait.until(ExpectedConditions.presenceOfElementLocated(
                By.cssSelector("input[name='username'], input[id='username']")
            ));
            usernameField.sendKeys("staff_user");
            
            WebElement passwordField = driver.findElement(
                By.cssSelector("input[type='password']")
            );
            passwordField.sendKeys("valid_password");
            
            WebElement loginButton = driver.findElement(
                By.cssSelector("button[type='submit']")
            );
            clickElement(loginButton);
            
            WebElement incidentReportingLink = wait.until(ExpectedConditions.presenceOfElementLocated(
                By.xpath("//a[contains(translate(.,'ABCDEFGHIJKLMNOPQRSTUVWXYZ','abcdefghijklmnopqrstuvwxyz'),'incident')]")
            ));
            clickElement(incidentReportingLink);
            
            WebElement incidentTypeField = wait.until(ExpectedConditions.presenceOfElementLocated(
                By.cssSelector("input[name*='type' i], select[name*='type' i]")
            ));
            incidentTypeField.sendKeys("Safety");
            
            WebElement dateTimeField = driver.findElement(
                By.cssSelector("input[type='date'], input[type='datetime-local'], input[name*='date' i]")
            );
            dateTimeField.sendKeys("2024-06-01");
            
            WebElement descriptionField = driver.findElement(
                By.cssSelector("textarea[name*='description' i], textarea[id*='description' i]")
            );
            descriptionField.sendKeys("Test incident description");
            
            WebElement locationField = driver.findElement(
                By.cssSelector("input[name*='location' i], input[id*='location' i]")
            );
            locationField.sendKeys("Main Office");
            
            WebElement submitButton = driver.findElement(
                By.cssSelector("button[type='submit'], input[type='submit']")
            );
            clickElement(submitButton);
            
            WebElement confirmationMessage = wait.until(ExpectedConditions.presenceOfElementLocated(
                By.xpath("//*[contains(translate(.,'ABCDEFGHIJKLMNOPQRSTUVWXYZ','abcdefghijklmnopqrstuvwxyz'),'submitted') or contains(translate(.,'ABCDEFGHIJKLMNOPQRSTUVWXYZ','abcdefghijklmnopqrstuvwxyz'),'success')]")
            ));
            
            Assert.assertTrue(confirmationMessage.isDisplayed(), "Incident report submission confirmation not displayed");
        } catch (Exception e) {
            Assert.fail("Submit incident report failed: " + e.getMessage());
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