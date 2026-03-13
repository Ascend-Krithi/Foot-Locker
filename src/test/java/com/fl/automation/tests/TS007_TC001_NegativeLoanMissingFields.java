package com.fl.automation.tests;

import com.fl.automation.core.BaseTest;
import com.fl.automation.pages.EcoHomeLoanPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TS007_TC001_NegativeLoanMissingFields extends BaseTest {

    @Test(description = "TC_4120: SCRUM-19509 TS-007 TC-001 - Negative test: loan application with missing fields")
    public void testNegativeLoanMissingFields() {
        EcoHomeLoanPage loanPage = new EcoHomeLoanPage(driver);
        
        driver.get("https://eco-home-hub.example.com/loan");
        loanPage.applyForLoan("", "");
        
        boolean isErrorDisplayed = loanPage.isErrorDisplayed();
        Assert.assertTrue(isErrorDisplayed, "Error should be displayed for missing fields");
    }
}