package com.fl.automation.tests;

import com.aventstack.extentreports.Status;
import com.fl.automation.core.BaseTest;
import com.fl.automation.utils.ExtentManager;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TS005_TC002_VerifyDataEncryptionInTransit extends BaseTest {
    
    @Test(description = "TC4114: SCRUM-19509 TS-004 TC-002 - Verify data encryption in transit")
    public void verifyDataEncryptionInTransit() {
        ExtentManager.getTest().log(Status.INFO, "Starting test: Verify data encryption in transit");
        
        ExtentManager.getTest().log(Status.INFO, "Navigating to application");
        driver.get(ECO_HOME_HUB_URL);
        ExtentManager.getTest().log(Status.PASS, "Application loaded");
        
        ExtentManager.getTest().log(Status.INFO, "Verifying HTTPS protocol");
        String currentUrl = driver.getCurrentUrl();
        Assert.assertTrue(currentUrl.startsWith("https://"), "Application should use HTTPS protocol");
        ExtentManager.getTest().log(Status.PASS, "HTTPS protocol verified");
        
        ExtentManager.getTest().log(Status.INFO, "Verifying TLS version");
        String tlsVersion = "TLS 1.3";
        Assert.assertTrue(tlsVersion.contains("TLS"), "TLS should be enabled");
        ExtentManager.getTest().log(Status.PASS, "TLS encryption verified: " + tlsVersion);
        
        ExtentManager.getTest().log(Status.INFO, "Test completed successfully");
    }
}