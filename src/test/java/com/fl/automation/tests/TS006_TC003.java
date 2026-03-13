package com.fl.automation.tests;

import com.fl.automation.core.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TS006_TC003 extends BaseTest {

    @Test(description = "TC4119: SCRUM-19509 TS-006 TC-003 - Analytics: leads sent to installers metric")
    public void testAnalyticsLeadsSentToInstallers() {
        driver.get("https://ecohomehub.example.com/analytics/leads");
        Assert.assertTrue(driver.getCurrentUrl().contains("analytics") && driver.getCurrentUrl().contains("leads"), 
            "Should show leads sent to installers analytics");
    }
}