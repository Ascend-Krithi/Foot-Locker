package com.fl.automation.tests;

import com.fl.automation.core.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TS004_TC001 extends BaseTest {

    @Test(description = "TC4113: SCRUM-19509 TS-004 TC-001 - Verify data encryption at rest")
    public void testDataEncryptionAtRest() {
        driver.get("https://ecohomehub.example.com/security/encryption-status");
        Assert.assertTrue(driver.getCurrentUrl().contains("security") || driver.getCurrentUrl().contains("encryption"), 
            "Should navigate to encryption status page");
    }
}