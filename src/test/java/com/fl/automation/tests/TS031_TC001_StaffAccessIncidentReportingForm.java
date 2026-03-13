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
 * TC_ID: 4192
 * Test Case: Staff Access Incident Reporting Form
 * Description: Launch application, enter valid Staff credentials, click 'Login', navigate to Incident Reporting Form from main menu or dashboard, verify Incident Reporting Form accessible.
 */
public class TS031_TC001_StaffAccessIncidentReportingForm extends BaseTest {

    @Test
    public void staffAccessIncidentReportingForm() {
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
                By.xpath("//a[contains(translate(.,'ABCDEFGHIJKLMNOPQRSTUVWXYZ','abcdefghijklmnopqrstuvwxyz'),'incident') and contains(translate(.,'ABCDEFGHIJKLMNOPQRSTUVWXYZ','abcdefghijklmnopqrstuvwxyz'),'report')]")
            ));
            clickElement(incidentReportingLink);
            
            WebElement incidentForm = wait.until(ExpectedConditions.presenceOfElementLocated(
                By.cssSelector("form[class*='incident' i], form[id*='incident' i]")
            ));
            
            Assert.assertTrue(incidentForm.isDisplayed(), "Incident Reporting Form not accessible");
        } catch (Exception e) {
            Assert.fail("Staff access incident reporting form failed: " + e.getMessage());
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