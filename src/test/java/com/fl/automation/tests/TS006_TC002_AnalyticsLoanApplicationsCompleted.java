package com.fl.automation.tests;

import com.fl.automation.core.BaseTest;
import org.openqa.selenium.JavascriptExecutor;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TS006_TC002_AnalyticsLoanApplicationsCompleted extends BaseTest {

    @Test(description = "TC_4118: SCRUM-19509 TS-006 TC-002 - Analytics loan applications completed")
    public void verifyAnalyticsLoanApplicationsCompleted() {
        driver.get(baseUrl);
        
        JavascriptExecutor js = (JavascriptExecutor) driver;
        Object analyticsPresent = js.executeScript("return typeof window.dataLayer !== 'undefined' || typeof window.ga !== 'undefined';");
        Assert.assertTrue((Boolean) analyticsPresent, "Analytics tracking for completed applications should be present");
    }
}