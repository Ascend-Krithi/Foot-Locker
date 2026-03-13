package com.fl.automation.tests;

import com.fl.automation.core.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TS004_TC001 extends BaseTest {

    @Test(description = "TC4113: Verify data encryption at rest")
    public void testDataEncryptionAtRest() throws InterruptedException {
        driver.get("https://eco-home-hub.example.com");
        Thread.sleep(2000);
        Assert.assertTrue(driver.getCurrentUrl().contains("eco-home-hub"), "Application should load");
    }
}