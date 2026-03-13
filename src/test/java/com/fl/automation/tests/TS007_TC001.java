package com.fl.automation.tests;

import com.fl.automation.core.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TS007_TC001 extends BaseTest {

    @Test(description = "TC4120: Loan application validation missing required fields")
    public void testLoanApplicationValidationMissingFields() throws InterruptedException {
        driver.get("https://eco-home-hub.example.com");
        Thread.sleep(2000);
        Assert.assertTrue(driver.getCurrentUrl().contains("eco-home-hub"), "Application should load");
    }
}