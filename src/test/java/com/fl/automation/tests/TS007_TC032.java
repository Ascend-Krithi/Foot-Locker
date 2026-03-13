package com.fl.automation.tests;

import com.fl.automation.core.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TS007_TC032 extends BaseTest {

    @Test(description = "TC4147: User dashboard verification")
    public void testUserDashboard() throws InterruptedException {
        driver.get("https://eco-renovation.com/marketplace/dashboard");
        Thread.sleep(2000);
        Assert.assertTrue(driver.getCurrentUrl().contains("/dashboard"), "Dashboard should load");
    }
}