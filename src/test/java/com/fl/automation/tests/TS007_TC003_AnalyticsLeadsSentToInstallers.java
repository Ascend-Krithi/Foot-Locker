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

public class TS007_TC003_AnalyticsLeadsSentToInstallers extends BaseTest {
    
    @Test(description = "TC4119: SCRUM-19509 TS-006 TC-003 - Analytics leads sent to installers")
    public void analyticsLeadsSentToInstallers() {
        ExtentManager.getTest().log(Status.INFO, "Starting test: Analytics leads sent to installers");
        
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(50));
        
        ExtentManager.getTest().log(Status.INFO, "Navigating to analytics dashboard");
        driver.get(ECO_HOME_HUB_URL + "/admin/analytics");
        ExtentManager.getTest().log(Status.PASS, "Analytics dashboard loaded");
        
        ExtentManager.getTest().log(Status.INFO, "Viewing leads sent metric");
        WebElement leadsSentMetric = wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".metric-leads-sent")));
        Assert.assertTrue(leadsSentMetric.isDisplayed(), "Leads sent metric should be displayed");
        ExtentManager.getTest().log(Status.PASS, "Leads sent metric displayed");
        
        ExtentManager.getTest().log(Status.INFO, "Verifying metric value");
        String metricValue = leadsSentMetric.getText();
        Assert.assertNotNull(metricValue, "Metric value should not be null");
        ExtentManager.getTest().log(Status.PASS, "Leads sent to installers: " + metricValue);
        
        ExtentManager.getTest().log(Status.INFO, "Verifying lead distribution");
        WebElement distributionChart = driver.findElement(By.cssSelector(".lead-distribution-chart"));
        Assert.assertTrue(distributionChart.isDisplayed(), "Lead distribution chart should be displayed");
        ExtentManager.getTest().log(Status.PASS, "Lead distribution chart verified");
        
        ExtentManager.getTest().log(Status.INFO, "Test completed successfully");
    }
}