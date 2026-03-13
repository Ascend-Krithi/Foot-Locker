package com.fl.automation.tests;

import com.fl.automation.core.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TS010_TC035 extends BaseTest {

    @Test(description = "TC4150: User logout")
    public void testUserLogout() throws InterruptedException {
        driver.get("https://eco-renovation.com/marketplace/logout");
        Thread.sleep(2000);
        Assert.assertTrue(driver.getCurrentUrl().contains("eco-renovation.com"), "Logout should complete");
    }
}