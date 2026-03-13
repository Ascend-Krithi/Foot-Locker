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
import java.util.List;

public class TS002_TC003_EcoHomeHubInstallerSearch extends BaseTest {
    
    @Test(description = "TC4106: SCRUM-19509 TS-001 TC-003 - Eco Home Hub installer search")
    public void ecoHomeHubInstallerSearch() {
        ExtentManager.getTest().log(Status.INFO, "Starting test: Eco Home Hub installer search");
        
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(50));
        
        ExtentManager.getTest().log(Status.INFO, "Navigating to Eco Home Hub installer search");
        driver.get(ECO_HOME_HUB_URL + "/find-installer");
        ExtentManager.getTest().log(Status.PASS, "Installer search page loaded");
        
        ExtentManager.getTest().log(Status.INFO, "Entering search criteria");
        WebElement zipCodeInput = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("zipCode")));
        zipCodeInput.sendKeys("02101");
        
        WebElement serviceTypeSelect = driver.findElement(By.id("serviceType"));
        serviceTypeSelect.sendKeys("Solar Panel Installation");
        
        ExtentManager.getTest().log(Status.PASS, "Search criteria entered");
        
        ExtentManager.getTest().log(Status.INFO, "Clicking search button");
        WebElement searchBtn = driver.findElement(By.cssSelector("button[type='submit']"));
        searchBtn.click();
        ExtentManager.getTest().log(Status.PASS, "Search button clicked");
        
        ExtentManager.getTest().log(Status.INFO, "Verifying installer results");
        List<WebElement> installerResults = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector(".installer-card")));
        Assert.assertTrue(installerResults.size() > 0, "Installer results should be displayed");
        ExtentManager.getTest().log(Status.PASS, "Found " + installerResults.size() + " installers");
        
        ExtentManager.getTest().log(Status.INFO, "Test completed successfully");
    }
}