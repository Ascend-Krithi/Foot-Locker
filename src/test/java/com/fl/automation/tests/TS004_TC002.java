package com.fl.automation.tests;

import com.fl.automation.core.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TS004_TC002 extends BaseTest {

    @Test(description = "TC4114: Verify data encryption in transit")
    public void testDataEncryptionInTransit() throws InterruptedException {
        driver.get("https://eco-home-hub.example.com");
        Thread.sleep(2000);
        Assert.assertTrue(driver.getCurrentUrl().startsWith("https://"), "Application should use HTTPS");
    }
}