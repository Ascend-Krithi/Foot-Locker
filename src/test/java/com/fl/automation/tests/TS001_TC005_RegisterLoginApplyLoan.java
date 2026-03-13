package com.fl.automation.tests;

import com.fl.automation.core.BaseTest;
import com.fl.automation.pages.EcoHomeRegistrationPage;
import com.fl.automation.pages.EcoHomeLoanPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TS001_TC005_RegisterLoginApplyLoan extends BaseTest {

    @Test(description = "TC_4105: SCRUM-19509 TS-001 TC-002 - Register, login, apply for loan")
    public void testRegisterLoginApplyLoan() {
        EcoHomeRegistrationPage registrationPage = new EcoHomeRegistrationPage(driver);
        EcoHomeLoanPage loanPage = new EcoHomeLoanPage(driver);
        
        registrationPage.navigateToEcoHomeHub();
        registrationPage.registerCustomer("Jane", "Smith", "jane.smith@example.com", "SecurePass456");
        
        loanPage.applyForLoan("50000", "75000");
        
        boolean isApplicationSuccessful = loanPage.isApplicationSuccessful();
        Assert.assertTrue(isApplicationSuccessful, "Loan application should be successful");
    }
}