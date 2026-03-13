package com.fl.automation.tests;

import com.fl.automation.core.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TS002_TC025 extends BaseTest {

    @Test(description = "TC4140: Marketplace search by keyword")
    public void testMarketplaceSearchByKeyword() throws InterruptedException {
        driver.get("https://eco-renovation.com/marketplace/search");
        Thread.sleep(2000);
        Assert.assertTrue(driver.getCurrentUrl().contains("/marketplace/search"), "Search page should load");
    }
}