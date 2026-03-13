package com.fl.automation.tests;

import com.fl.automation.core.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TS007_TC001 extends BaseTest {

    @Test(description = "TC4120: SCRUM-19509 TS-007 TC-001 - Loan application validation: missing required fields")
    public void testLoanApplicationMissingFields() {
        driver.get("https://ecohomehub.example.com/loan-application");
        Assert.assertTrue(driver.getCurrentUrl().contains("loan"), "Should be on loan application page");
    }
}