package com.fl.automation.tests;

import com.fl.automation.core.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TS004_TC002 extends BaseTest {

    @Test(description = "TC4114: SCRUM-19509 TS-004 TC-002 - Verify data encryption in transit")
    public void testDataEncryptionInTransit() {
        driver.get("https://ecohomehub.example.com");
        String currentUrl = driver.getCurrentUrl();
        Assert.assertTrue(currentUrl.startsWith("https://"), "Should use HTTPS for encryption in transit");
    }
}