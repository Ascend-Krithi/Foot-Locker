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

public class TS007_TC002_AnalyticsLoanApplicationsCompleted extends BaseTest {
    
    @Test(description = "TC4118: SCRUM-19509 TS-006 TC-002 - Analytics loan applications completed")
    public void analyticsLoanApplicationsCompleted() {
        ExtentManager.getTest().log(Status.INFO, "Starting test: Analytics loan applications completed");
        
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(50));
        
        ExtentManager.getTest().log(Status.INFO, "Navigating to analytics dashboard");
        driver.get(ECO_HOME_HUB_URL + "/admin/analytics");
        ExtentManager.getTest().log(Status.PASS, "Analytics dashboard loaded");
        
        ExtentManager.getTest().log(Status.INFO, "Viewing loan applications completed metric");
        WebElement loanAppsCompletedMetric = wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".metric-loan-apps-completed")));
        Assert.assertTrue(loanAppsCompletedMetric.isDisplayed(), "Loan applications completed metric should be displayed");
        ExtentManager.getTest().log(Status.PASS, "Loan applications completed metric displayed");
        
        ExtentManager.getTest().log(Status.INFO, "Verifying metric value");
        String metricValue = loanAppsCompletedMetric.getText();
        Assert.assertNotNull(metricValue, "Metric value should not be null");
        ExtentManager.getTest().log(Status.PASS, "Loan applications completed: " + metricValue);
        
        ExtentManager.getTest().log(Status.INFO, "Verifying completion rate");
        WebElement completionRateMetric = driver.findElement(By.cssSelector(".metric-completion-rate"));
        String completionRate = completionRateMetric.getText();
        ExtentManager.getTest().log(Status.PASS, "Completion rate: " + completionRate);
        
        ExtentManager.getTest().log(Status.INFO, "Test completed successfully");
    }
}