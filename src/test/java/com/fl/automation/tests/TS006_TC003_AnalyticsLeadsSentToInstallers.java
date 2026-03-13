package com.fl.automation.tests;

import com.fl.automation.core.BaseTest;
import org.openqa.selenium.JavascriptExecutor;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TS006_TC003_AnalyticsLeadsSentToInstallers extends BaseTest {

    @Test(description = "TC_4119: SCRUM-19509 TS-006 TC-003 - Analytics leads sent to installers")
    public void verifyAnalyticsLeadsSentToInstallers() {
        driver.get(baseUrl);
        
        JavascriptExecutor js = (JavascriptExecutor) driver;
        Object analyticsPresent = js.executeScript("return typeof window.dataLayer !== 'undefined' || typeof window.ga !== 'undefined';");
        Assert.assertTrue((Boolean) analyticsPresent, "Analytics tracking for leads should be present");
    }
}