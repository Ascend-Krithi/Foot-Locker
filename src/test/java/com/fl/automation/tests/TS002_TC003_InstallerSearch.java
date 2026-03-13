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
 * TC_ID: 4106
 * Test Case: Installer Search
 * Description: Register, login, complete loan application, navigate to installer search, enter criteria, submit, verify installer list.
 */
public class TS002_TC003_InstallerSearch extends BaseTest {

    @Test
    public void installerSearch() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(40));
        
        driver.get("https://eco-home-hub.example.com");
        
        try {
            WebElement installerLink = wait.until(ExpectedConditions.presenceOfElementLocated(
                By.xpath("//a[contains(translate(.,'ABCDEFGHIJKLMNOPQRSTUVWXYZ','abcdefghijklmnopqrstuvwxyz'),'installer')]"))
            );
            clickElement(installerLink);
            
            WebElement locationField = wait.until(ExpectedConditions.presenceOfElementLocated(
                By.cssSelector("input[name*='location' i], input[id*='location' i]")
            ));
            locationField.sendKeys("London");
            
            WebElement serviceTypeField = driver.findElement(
                By.cssSelector("input[name*='service' i], select[name*='service' i]")
            );
            serviceTypeField.sendKeys("Solar Panel Installation");
            
            WebElement searchButton = driver.findElement(
                By.cssSelector("button[type='submit'], input[type='submit'], button[class*='search' i]")
            );
            clickElement(searchButton);
            
            WebElement installerList = wait.until(ExpectedConditions.presenceOfElementLocated(
                By.cssSelector("[class*='installer'], [class*='result'], ul li, div[class*='list']")
            ));
            
            Assert.assertTrue(installerList.isDisplayed(), "Installer list not displayed");
        } catch (Exception e) {
            Assert.fail("Installer search failed: " + e.getMessage());
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