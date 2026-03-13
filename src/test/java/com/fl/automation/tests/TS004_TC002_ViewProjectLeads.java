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
 * TC_ID: 4112
 * Test Case: View Project Leads
 * Description: Login as installer, navigate to Project Leads section, verify new project leads displayed with correct details.
 */
public class TS004_TC002_ViewProjectLeads extends BaseTest {

    @Test
    public void viewProjectLeads() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(40));
        
        driver.get("https://installer.eco-home-hub.example.com");
        
        try {
            WebElement usernameField = wait.until(ExpectedConditions.presenceOfElementLocated(
                By.cssSelector("input[name='username'], input[id='username']")
            ));
            usernameField.sendKeys("installer");
            
            WebElement passwordField = driver.findElement(
                By.cssSelector("input[type='password'], input[name='password']")
            );
            passwordField.sendKeys("InstallerPassword123");
            
            WebElement loginButton = driver.findElement(
                By.cssSelector("button[type='submit'], input[type='submit']")
            );
            clickElement(loginButton);
            
            WebElement projectLeadsLink = wait.until(ExpectedConditions.presenceOfElementLocated(
                By.xpath("//a[contains(translate(.,'ABCDEFGHIJKLMNOPQRSTUVWXYZ','abcdefghijklmnopqrstuvwxyz'),'project') and contains(translate(.,'ABCDEFGHIJKLMNOPQRSTUVWXYZ','abcdefghijklmnopqrstuvwxyz'),'lead')]")
            ));
            clickElement(projectLeadsLink);
            
            WebElement projectLeadDetails = wait.until(ExpectedConditions.presenceOfElementLocated(
                By.xpath("//*[contains(text(),'Install solar panels') or contains(text(),'John Doe')]")
            ));
            
            Assert.assertTrue(projectLeadDetails.isDisplayed(), "Project lead details not displayed");
        } catch (Exception e) {
            Assert.fail("View project leads failed: " + e.getMessage());
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