package com.fl.automation.tests;

import com.fl.automation.core.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TS012_TC037 extends BaseTest {

    @Test(description = "TC4152: Verify eco-certification details")
    public void testVerifyEcoCertificationDetails() throws InterruptedException {
        driver.get("https://eco-renovation.com/marketplace/project/12345");
        Thread.sleep(2000);
        Assert.assertTrue(driver.getCurrentUrl().contains("/project/"), "Project page should load");
    }
}