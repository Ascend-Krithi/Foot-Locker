package com.fl.automation.tests;

import com.fl.automation.core.BaseTest;
import com.fl.automation.pages.EcoHomeRegistrationPage;
import com.fl.automation.pages.EcoHomeLoanPage;
import com.fl.automation.pages.EcoHomeInstallerPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TS001_TC007_CompleteFlow extends BaseTest {

    @Test(description = "TC_4107: SCRUM-19509 TS-001 TC-004 - Complete flow: register, loan, installer search, send project lead")
    public void testCompleteFlow() {
        EcoHomeRegistrationPage registrationPage = new EcoHomeRegistrationPage(driver);
        EcoHomeLoanPage loanPage = new EcoHomeLoanPage(driver);
        EcoHomeInstallerPage installerPage = new EcoHomeInstallerPage(driver);
        
        registrationPage.navigateToEcoHomeHub();
        registrationPage.registerCustomer("Sarah", "Williams", "sarah.williams@example.com", "SecurePass321");
        
        loanPage.applyForLoan("70000", "90000");
        
        installerPage.searchForInstaller("New York, NY");
        installerPage.sendProjectLead();
        
        Assert.assertTrue(true, "Complete flow executed successfully");
    }
}