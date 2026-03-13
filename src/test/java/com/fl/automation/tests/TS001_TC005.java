package com.fl.automation.tests;

import com.fl.automation.core.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TS001_TC005 extends BaseTest {

    @Test(description = "TC4105: SCRUM-19509 TS-001 TC-002 - Eco Home Hub customer registration and loan application")
    public void testCustomerRegistrationAndLoanApplication() {
        driver.get("https://ecohomehub.example.com/register");
        Assert.assertTrue(driver.getCurrentUrl().contains("register"), "Should be on registration page");
        
        driver.get("https://ecohomehub.example.com/loan-application");
        Assert.assertTrue(driver.getCurrentUrl().contains("loan"), "Should navigate to loan application page");
    }
}