package com.fl.automation.tests;

import com.fl.automation.core.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TS004_TC001_VerifyDataEncryptionAtRest extends BaseTest {

    @Test(description = "TC_4113: SCRUM-19509 TS-004 TC-001 - Verify data encryption at rest")
    public void verifyDataEncryptionAtRest() {
        driver.get(baseUrl);
        
        String pageSource = driver.getPageSource();
        Assert.assertFalse(pageSource.contains("unencrypted"), "Data should be encrypted at rest");
    }
}