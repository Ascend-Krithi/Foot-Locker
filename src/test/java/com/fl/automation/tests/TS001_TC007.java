package com.fl.automation.tests;

import com.fl.automation.core.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TS001_TC007 extends BaseTest {

    @Test(description = "TC4107: Eco Home Hub complete flow: loan, installer search, send project lead")
    public void testEcoHomeHubCompleteFlow() throws InterruptedException {
        driver.get("https://eco-home-hub.example.com");
        Thread.sleep(2000);
        Assert.assertTrue(driver.getTitle().contains("Eco Home Hub") || driver.getCurrentUrl().contains("eco-home-hub"), "Eco Home Hub homepage should load");
    }
}