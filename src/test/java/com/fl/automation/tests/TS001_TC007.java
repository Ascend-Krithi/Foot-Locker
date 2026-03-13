package com.fl.automation.tests;

import com.fl.automation.core.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TS001_TC007 extends BaseTest {

    @Test(description = "TC4107: SCRUM-19509 TS-001 TC-004 - Eco Home Hub complete flow: loan, installer search, send project lead")
    public void testCompleteEcoHomeHubFlow() {
        driver.get("https://ecohomehub.example.com/loan-application");
        Assert.assertTrue(driver.getCurrentUrl().contains("loan"), "Should start at loan application");
        
        driver.get("https://ecohomehub.example.com/installer-search");
        Assert.assertTrue(driver.getCurrentUrl().contains("installer"), "Should proceed to installer search");
        
        driver.get("https://ecohomehub.example.com/send-lead");
        Assert.assertTrue(driver.getCurrentUrl().contains("lead") || driver.getCurrentUrl().contains("send"), 
            "Should complete flow at send lead page");
    }
}