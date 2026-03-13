package com.fl.automation.tests;

import com.aventstack.extentreports.Status;
import com.fl.automation.core.BaseTest;
import com.fl.automation.utils.ExtentManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;

public class TS003_TC003_VerifyApprovedInstallerInMarketplace extends BaseTest {
    
    @Test(description = "TC4110: SCRUM-19509 TS-002 TC-003 - Verify approved installer in marketplace")
    public void verifyApprovedInstallerInMarketplace() {
        ExtentManager.getTest().log(Status.INFO, "Starting test: Verify approved installer in marketplace");
        
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(50));
        
        ExtentManager.getTest().log(Status.INFO, "Navigating to marketplace");
        driver.get(ECO_HOME_HUB_URL + "/marketplace");
        ExtentManager.getTest().log(Status.PASS, "Marketplace page loaded");
        
        ExtentManager.getTest().log(Status.INFO, "Searching for approved installer");
        WebElement searchInput = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("searchInstaller")));
        searchInput.sendKeys("Solar Pro Installers");
        
        WebElement searchBtn = driver.findElement(By.cssSelector("button[type='submit']"));
        searchBtn.click();
        ExtentManager.getTest().log(Status.PASS, "Search executed");
        
        ExtentManager.getTest().log(Status.INFO, "Verifying installer appears in results");
        WebElement installerCard = wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".installer-card")));
        Assert.assertTrue(installerCard.isDisplayed(), "Installer card should be displayed");
        ExtentManager.getTest().log(Status.PASS, "Approved installer found in marketplace");
        
        ExtentManager.getTest().log(Status.INFO, "Verifying approved badge");
        WebElement approvedBadge = installerCard.findElement(By.cssSelector(".approved-badge"));
        Assert.assertTrue(approvedBadge.isDisplayed(), "Approved badge should be displayed");
        ExtentManager.getTest().log(Status.PASS, "Approved badge verified");
        
        ExtentManager.getTest().log(Status.INFO, "Test completed successfully");
    }
}