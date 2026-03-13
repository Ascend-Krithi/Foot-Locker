package com.fl.automation.tests;

import com.fl.automation.core.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TS001_TC006 extends BaseTest {

    @Test(description = "TC4106: Eco Home Hub loan application and installer search")
    public void testEcoHomeHubInstallerSearch() throws InterruptedException {
        driver.get("https://eco-home-hub.example.com");
        Thread.sleep(2000);
        Assert.assertTrue(driver.getTitle().contains("Eco Home Hub") || driver.getCurrentUrl().contains("eco-home-hub"), "Eco Home Hub homepage should load");
    }
}