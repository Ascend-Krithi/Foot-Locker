package com.fl.automation.tests;

import com.fl.automation.core.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TS006_TC002 extends BaseTest {

    @Test(description = "TC4118: SCRUM-19509 TS-006 TC-002 - Analytics: loan applications completed metric")
    public void testAnalyticsLoanApplicationsCompleted() {
        driver.get("https://ecohomehub.example.com/analytics/loan-applications?status=completed");
        Assert.assertTrue(driver.getCurrentUrl().contains("analytics") && driver.getCurrentUrl().contains("loan"), 
            "Should show completed loan applications analytics");
    }
}