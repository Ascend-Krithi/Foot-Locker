package com.fl.automation.tests;

import com.fl.automation.core.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TS004_TC002_DataEncryptionInTransit extends BaseTest {

    @Test(description = "TC_4114: SCRUM-19509 TS-004 TC-002 - Verify data encryption in transit")
    public void testDataEncryptionInTransit() {
        driver.get("https://eco-home-hub.example.com");
        
        String currentUrl = driver.getCurrentUrl();
        Assert.assertTrue(currentUrl.startsWith("https://"), "URL should use HTTPS protocol");
    }
}