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
 * TC_ID: 4142
 * Test Case: View Project Details
 * Description: From search results, click project, verify project details page loads, verify images, description, pricing, eco-certifications displayed.
 */
public class TS011_TC001_ViewProjectDetails extends BaseTest {

    @Test
    public void viewProjectDetails() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(40));
        
        driver.get("https://eco-renovation.com/marketplace/search");
        
        try {
            WebElement firstProject = wait.until(ExpectedConditions.presenceOfElementLocated(
                By.cssSelector("[class*='project'], [class*='result'], a[href*='project']")
            ));
            clickElement(firstProject);
            
            WebElement projectImage = wait.until(ExpectedConditions.presenceOfElementLocated(
                By.cssSelector("img[class*='project' i], img[alt*='project' i]")
            ));
            Assert.assertTrue(projectImage.isDisplayed(), "Project image not displayed");
            
            WebElement description = driver.findElement(
                By.xpath("//*[contains(@class,'description') or contains(@id,'description')]")
            );
            Assert.assertTrue(description.isDisplayed(), "Project description not displayed");
            
            WebElement pricing = driver.findElement(
                By.xpath("//*[contains(translate(.,'ABCDEFGHIJKLMNOPQRSTUVWXYZ','abcdefghijklmnopqrstuvwxyz'),'price') or contains(translate(.,'ABCDEFGHIJKLMNOPQRSTUVWXYZ','abcdefghijklmnopqrstuvwxyz'),'cost')]")
            );
            Assert.assertTrue(pricing.isDisplayed(), "Pricing information not displayed");
            
            WebElement ecoCertifications = driver.findElement(
                By.xpath("//*[contains(translate(.,'ABCDEFGHIJKLMNOPQRSTUVWXYZ','abcdefghijklmnopqrstuvwxyz'),'eco') or contains(translate(.,'ABCDEFGHIJKLMNOPQRSTUVWXYZ','abcdefghijklmnopqrstuvwxyz'),'certification')]")
            );
            Assert.assertTrue(ecoCertifications.isDisplayed(), "Eco-certifications not displayed");
        } catch (Exception e) {
            Assert.fail("View project details failed: " + e.getMessage());
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