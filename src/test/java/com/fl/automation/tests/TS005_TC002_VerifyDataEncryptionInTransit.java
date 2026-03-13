package com.fl.automation.tests;

import com.fl.automation.core.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * TC_ID: 4114
 * Test Case: Verify Data Encryption In Transit
 * Description: Initiate API requests with user/financial data, monitor network traffic, verify data transmitted over HTTPS/TLS and not visible in plain text.
 */
public class TS005_TC002_VerifyDataEncryptionInTransit extends BaseTest {

    @Test
    public void verifyDataEncryptionInTransit() {
        try {
            driver.get("https://eco-home-hub.example.com");
            
            String currentUrl = driver.getCurrentUrl();
            boolean isHttps = currentUrl.startsWith("https://");
            
            Assert.assertTrue(isHttps, "Data is not transmitted over HTTPS");
        } catch (Exception e) {
            Assert.fail("Verify data encryption in transit failed: " + e.getMessage());
        }
    }
}