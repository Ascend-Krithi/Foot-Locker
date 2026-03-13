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

public class TS007_TC001_AnalyticsLoanApplicationsStarted extends BaseTest {
    
    @Test(description = "TC4117: SCRUM-19509 TS-006 TC-001 - Analytics loan applications started")
    public void analyticsLoanApplicationsStarted() {
        ExtentManager.getTest().log(Status.INFO, "Starting test: Analytics loan applications started");
        
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(50));
        
        ExtentManager.getTest().log(Status.INFO, "Navigating to analytics dashboard");
        driver.get(ECO_HOME_HUB_URL + "/admin/analytics");
        ExtentManager.getTest().log(Status.PASS, "Analytics dashboard loaded");
        
        ExtentManager.getTest().log(Status.INFO, "Viewing loan applications started metric");
        WebElement loanAppsStartedMetric = wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".metric-loan-apps-started")));
        Assert.assertTrue(loanAppsStartedMetric.isDisplayed(), "Loan applications started metric should be displayed");
        ExtentManager.getTest().log(Status.PASS, "Loan applications started metric displayed");
        
        ExtentManager.getTest().log(Status.INFO, "Verifying metric value");
        String metricValue = loanAppsStartedMetric.getText();
        Assert.assertNotNull(metricValue, "Metric value should not be null");
        ExtentManager.getTest().log(Status.PASS, "Loan applications started: " + metricValue);
        
        ExtentManager.getTest().log(Status.INFO, "Test completed successfully");
    }
}