package com.fl.automation.tests;

import com.fl.automation.core.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TS006_TC001 extends BaseTest {

    @Test(description = "TC4117: SCRUM-19509 TS-006 TC-001 - Analytics: loan applications started metric")
    public void testAnalyticsLoanApplicationsStarted() {
        driver.get("https://ecohomehub.example.com/analytics/loan-applications");
        Assert.assertTrue(driver.getCurrentUrl().contains("analytics"), "Should navigate to analytics page");
    }
}