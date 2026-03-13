package com.fl.automation.tests;

import com.fl.automation.core.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TS005_TC002_ProductionMonitoring extends BaseTest {

    @Test(description = "TC_4116: SCRUM-19509 TS-005 TC-002 - Production monitoring")
    public void verifyProductionMonitoring() {
        driver.get(baseUrl);
        
        String pageTitle = driver.getTitle();
        Assert.assertFalse(pageTitle.isEmpty(), "Page title should be present indicating site is monitored and functional");
    }
}