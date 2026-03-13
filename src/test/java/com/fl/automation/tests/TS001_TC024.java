package com.fl.automation.tests;

import com.fl.automation.core.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TS001_TC024 extends BaseTest {

    @Test(description = "TC4139: Marketplace home page load and elements")
    public void testMarketplaceHomePage() throws InterruptedException {
        driver.get("https://eco-renovation.com/marketplace/home");
        Thread.sleep(3000);
        Assert.assertTrue(driver.getCurrentUrl().contains("/marketplace/home"), "Marketplace home page should load");
    }
}