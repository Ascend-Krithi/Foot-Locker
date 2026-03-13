package com.fl.automation.tests;

import com.fl.automation.core.BaseTest;
import com.fl.automation.pages.EcoHomeLoanPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TS007_TC002_NegativeLoanInvalidValues extends BaseTest {

    @Test(description = "TC_4121: SCRUM-19509 TS-007 TC-002 - Negative test: loan application with invalid values")
    public void testNegativeLoanInvalidValues() {
        EcoHomeLoanPage loanPage = new EcoHomeLoanPage(driver);
        
        driver.get("https://eco-home-hub.example.com/loan");
        loanPage.applyForLoan("invalid", "invalid");
        
        boolean isErrorDisplayed = loanPage.isErrorDisplayed();
        Assert.assertTrue(isErrorDisplayed, "Error should be displayed for invalid values");
    }
}