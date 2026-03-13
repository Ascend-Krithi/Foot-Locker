package com.fl.automation.tests;

import com.fl.automation.core.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TS005_TC002 extends BaseTest {

    @Test(description = "TC4116: SCRUM-19509 TS-005 TC-002 - Production monitoring verification")
    public void testProductionMonitoringVerification() {
        driver.get("https://ecohomehub.example.com/monitoring/status");
        Assert.assertTrue(driver.getCurrentUrl().contains("monitoring") || driver.getCurrentUrl().contains("status"), 
            "Should access monitoring status page");
    }
}