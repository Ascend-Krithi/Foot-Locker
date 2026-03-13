package com.fl.automation.tests;

import org.testng.annotations.Test;
import org.testng.Assert;
import com.fl.automation.core.BaseTest;
import com.fl.automation.pages.EcoHomeHubHomePage;
import com.fl.automation.pages.LoanApplicationPage;

public class TS002_TC002_EcoHomeHub extends BaseTest {
    
    @Test
    public void testLoanApplication() {
        driver.get("https://ecohomehub.example.com/loan");
        LoanApplicationPage loanPage = new LoanApplicationPage(driver);
        loanPage.fillLoanApplication("50000", "Solar Panel Installation", "75000");
        loanPage.submitApplication();
        
        Assert.assertTrue(loanPage.isApplicationSubmitted(), "Loan application should be submitted");
    }
}