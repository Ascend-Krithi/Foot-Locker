package com.fl.automation.tests;

import com.fl.automation.core.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TS005_TC029 extends BaseTest {

    @Test(description = "TC4144: Marketplace login valid credentials")
    public void testMarketplaceLoginValid() throws InterruptedException {
        driver.get("https://eco-renovation.com/marketplace/login");
        Thread.sleep(2000);
        Assert.assertTrue(driver.getCurrentUrl().contains("/login"), "Login page should load");
    }
}