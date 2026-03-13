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

public class TS015_TC001_UserDashboardAccess extends BaseTest {
    
    @Test(description = "TC4147: SCRUM-19509 TS-007 TC-001 - User dashboard access")
    public void userDashboardAccess() {
        ExtentManager.getTest().log(Status.INFO, "Starting test: User dashboard access");
        
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(50));
        
        ExtentManager.getTest().log(Status.INFO, "Navigating to marketplace");
        driver.get(MARKETPLACE_URL);
        ExtentManager.getTest().log(Status.PASS, "Marketplace loaded");
        
        ExtentManager.getTest().log(Status.INFO, "Clicking Dashboard link");
        WebElement dashboardLink = wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Dashboard")));
        dashboardLink.click();
        ExtentManager.getTest().log(Status.PASS, "Dashboard link clicked");
        
        ExtentManager.getTest().log(Status.INFO, "Verifying dashboard page loaded");
        WebElement dashboardHeader = wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".dashboard-header")));
        Assert.assertTrue(dashboardHeader.isDisplayed(), "Dashboard header should be displayed");
        ExtentManager.getTest().log(Status.PASS, "Dashboard page loaded");
        
        ExtentManager.getTest().log(Status.INFO, "Verifying dashboard widgets");
        WebElement projectsWidget = driver.findElement(By.cssSelector(".widget-projects"));
        Assert.assertTrue(projectsWidget.isDisplayed(), "Projects widget should be displayed");
        
        WebElement applicationsWidget = driver.findElement(By.cssSelector(".widget-applications"));
        Assert.assertTrue(applicationsWidget.isDisplayed(), "Applications widget should be displayed");
        
        ExtentManager.getTest().log(Status.PASS, "Dashboard widgets verified");
        
        ExtentManager.getTest().log(Status.INFO, "Test completed successfully");
    }
}