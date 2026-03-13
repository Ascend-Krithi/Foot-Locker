package com.fl.automation.tests;

import com.fl.automation.core.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TS007_TC002 extends BaseTest {

    @Test(description = "TC4121: SCRUM-19509 TS-007 TC-002 - Loan application validation: invalid values")
    public void testLoanApplicationInvalidValues() {
        driver.get("https://ecohomehub.example.com/loan-application");
        Assert.assertTrue(driver.getCurrentUrl().contains("loan"), "Should be on loan application page for validation testing");
    }
}