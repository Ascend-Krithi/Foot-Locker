package com.fl.automation.tests;

import com.fl.automation.core.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TS005_TC001_ProductionDeployment extends BaseTest {

    @Test(description = "TC_4115: SCRUM-19509 TS-005 TC-001 - Verify production deployment")
    public void testProductionDeployment() {
        driver.get("https://eco-home-hub.example.com");
        
        String pageTitle = driver.getTitle();
        Assert.assertNotNull(pageTitle, "Page title should not be null");
        Assert.assertFalse(pageTitle.isEmpty(), "Page title should not be empty");
    }
}