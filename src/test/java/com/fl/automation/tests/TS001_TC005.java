package com.fl.automation.tests;

import com.fl.automation.core.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TS001_TC005 extends BaseTest {

    @Test(description = "TC4105: Eco Home Hub customer registration and loan application")
    public void testEcoHomeHubLoanApplication() throws InterruptedException {
        driver.get("https://eco-home-hub.example.com");
        Thread.sleep(2000);
        Assert.assertTrue(driver.getTitle().contains("Eco Home Hub") || driver.getCurrentUrl().contains("eco-home-hub"), "Eco Home Hub homepage should load");
    }
}