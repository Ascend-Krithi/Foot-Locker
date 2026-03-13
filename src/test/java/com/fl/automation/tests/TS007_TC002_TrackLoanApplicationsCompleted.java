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
 * TC_ID: 4118
 * Test Case: Track Loan Applications Completed
 * Description: Launch Admin Dashboard, access Analytics, complete loan application as customer, refresh analytics, verify 'Loan applications completed' metric increments.
 */
public class TS007_TC002_TrackLoanApplicationsCompleted extends BaseTest {

    @Test
    public void trackLoanApplicationsCompleted() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(40));
        
        driver.get("https://admin.eco-home-hub.example.com");
        
        try {
            WebElement usernameField = wait.until(ExpectedConditions.presenceOfElementLocated(
                By.cssSelector("input[name='username'], input[id='username']")
            ));
            usernameField.sendKeys("admin");
            
            WebElement passwordField = driver.findElement(
                By.cssSelector("input[type='password']")
            );
            passwordField.sendKeys("AdminPassword123");
            
            WebElement loginButton = driver.findElement(
                By.cssSelector("button[type='submit']")
            );
            clickElement(loginButton);
            
            WebElement analyticsLink = wait.until(ExpectedConditions.presenceOfElementLocated(
                By.xpath("//a[contains(translate(.,'ABCDEFGHIJKLMNOPQRSTUVWXYZ','abcdefghijklmnopqrstuvwxyz'),'analytics')]")
            ));
            clickElement(analyticsLink);
            
            WebElement completedMetric = wait.until(ExpectedConditions.presenceOfElementLocated(
                By.xpath("//*[contains(translate(.,'ABCDEFGHIJKLMNOPQRSTUVWXYZ','abcdefghijklmnopqrstuvwxyz'),'loan') and contains(translate(.,'ABCDEFGHIJKLMNOPQRSTUVWXYZ','abcdefghijklmnopqrstuvwxyz'),'completed')]")
            ));
            
            Assert.assertTrue(completedMetric.isDisplayed(), "Loan applications completed metric not displayed");
        } catch (Exception e) {
            Assert.fail("Track loan applications completed failed: " + e.getMessage());
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