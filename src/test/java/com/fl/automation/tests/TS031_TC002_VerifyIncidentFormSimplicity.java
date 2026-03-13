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
 * TC_ID: 4193
 * Test Case: Verify Incident Form Simplicity
 * Description: Launch application, enter valid Staff credentials, click 'Login', navigate to Incident Reporting Form, review form fields and layout, verify form contains only essential fields and is simple.
 */
public class TS031_TC002_VerifyIncidentFormSimplicity extends BaseTest {

    @Test
    public void verifyIncidentFormSimplicity() {
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
            
            WebElement incidentForm = wait.until(ExpectedConditions.presenceOfElementLocated(
                By.cssSelector("form[class*='incident' i], form[id*='incident' i]")
            ));
            
            List<WebElement> formFields = incidentForm.findElements(
                By.cssSelector("input, select, textarea")
            );
            
            Assert.assertTrue(formFields.size() > 0 && formFields.size() <= 10, "Form does not contain only essential fields");
        } catch (Exception e) {
            Assert.fail("Verify incident form simplicity failed: " + e.getMessage());
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