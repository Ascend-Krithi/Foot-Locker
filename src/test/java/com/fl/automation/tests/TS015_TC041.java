package com.fl.automation.tests;

import com.fl.automation.core.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TS015_TC041 extends BaseTest {

    @Test(description = "TC4156: Access help and support")
    public void testAccessHelpAndSupport() throws InterruptedException {
        driver.get("https://eco-renovation.com/marketplace/help");
        Thread.sleep(2000);
        Assert.assertTrue(driver.getCurrentUrl().contains("/help"), "Help page should load");
    }
}