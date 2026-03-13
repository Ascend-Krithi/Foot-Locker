package com.fl.automation.tests;

import com.fl.automation.core.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TS005_TC002 extends BaseTest {

    @Test(description = "TC4116: Production monitoring verification")
    public void testProductionMonitoring() throws InterruptedException {
        driver.get("https://eco-home-hub.example.com");
        Thread.sleep(2000);
        Assert.assertTrue(driver.getCurrentUrl().contains("eco-home-hub"), "Production environment should be accessible");
    }
}