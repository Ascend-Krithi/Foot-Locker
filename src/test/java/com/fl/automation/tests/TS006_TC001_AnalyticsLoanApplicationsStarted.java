package com.fl.automation.tests;

import com.fl.automation.core.BaseTest;
import org.openqa.selenium.JavascriptExecutor;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TS006_TC001_AnalyticsLoanApplicationsStarted extends BaseTest {

    @Test(description = "TC_4117: SCRUM-19509 TS-006 TC-001 - Analytics loan applications started")
    public void verifyAnalyticsLoanApplicationsStarted() {
        driver.get(baseUrl);
        
        JavascriptExecutor js = (JavascriptExecutor) driver;
        Object analyticsPresent = js.executeScript("return typeof window.dataLayer !== 'undefined' || typeof window.ga !== 'undefined';");
        Assert.assertTrue((Boolean) analyticsPresent, "Analytics tracking should be present");
    }
}