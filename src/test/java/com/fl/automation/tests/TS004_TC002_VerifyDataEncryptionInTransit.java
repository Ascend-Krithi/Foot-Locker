package com.fl.automation.tests;

import com.fl.automation.core.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TS004_TC002_VerifyDataEncryptionInTransit extends BaseTest {

    @Test(description = "TC_4114: SCRUM-19509 TS-004 TC-002 - Verify data encryption in transit")
    public void verifyDataEncryptionInTransit() {
        driver.get(baseUrl);
        
        String currentUrl = driver.getCurrentUrl();
        Assert.assertTrue(currentUrl.startsWith("https://"), "Connection should use HTTPS for encryption in transit");
    }
}