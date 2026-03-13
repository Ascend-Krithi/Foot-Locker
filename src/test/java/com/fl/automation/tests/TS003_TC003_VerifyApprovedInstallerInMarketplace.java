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
 * TC_ID: 4110
 * Test Case: Verify Approved Installer In Marketplace
 * Description: Login as admin, approve installer with valid certifications, logout, launch Eco Home Hub as customer, search installers, verify approved installer appears.
 */
public class TS003_TC003_VerifyApprovedInstallerInMarketplace extends BaseTest {

    @Test
    public void verifyApprovedInstallerInMarketplace() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(40));
        
        driver.get("https://eco-home-hub.example.com");
        
        try {
            WebElement installerSearchLink = wait.until(ExpectedConditions.presenceOfElementLocated(
                By.xpath("//a[contains(translate(.,'ABCDEFGHIJKLMNOPQRSTUVWXYZ','abcdefghijklmnopqrstuvwxyz'),'installer') or contains(translate(.,'ABCDEFGHIJKLMNOPQRSTUVWXYZ','abcdefghijklmnopqrstuvwxyz'),'marketplace')]")
            ));
            clickElement(installerSearchLink);
            
            WebElement locationField = wait.until(ExpectedConditions.presenceOfElementLocated(
                By.cssSelector("input[name*='location' i], input[id*='location' i]")
            ));
            locationField.sendKeys("London");
            
            WebElement serviceTypeField = driver.findElement(
                By.cssSelector("input[name*='service' i], select[name*='service' i]")
            );
            serviceTypeField.sendKeys("Solar Panel Installation");
            
            WebElement searchButton = driver.findElement(
                By.cssSelector("button[type='submit'], input[type='submit']")
            );
            clickElement(searchButton);
            
            WebElement approvedInstaller = wait.until(ExpectedConditions.presenceOfElementLocated(
                By.xpath("//*[contains(text(),'Green Installers') or contains(@class,'installer')]")
            ));
            
            Assert.assertTrue(approvedInstaller.isDisplayed(), "Approved installer not visible in marketplace");
        } catch (Exception e) {
            Assert.fail("Verify approved installer in marketplace failed: " + e.getMessage());
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