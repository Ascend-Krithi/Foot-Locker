package com.fl.automation.tests;

import com.fl.automation.core.BaseTest;
import com.fl.automation.pages.EcoHomeRegistrationPage;
import com.fl.automation.pages.EcoHomeLoanPage;
import com.fl.automation.pages.EcoHomeInstallerPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TS001_TC006_RegisterLoanInstallerSearch extends BaseTest {

    @Test(description = "TC_4106: SCRUM-19509 TS-001 TC-003 - Register, apply for loan, search for installer")
    public void testRegisterLoanInstallerSearch() {
        EcoHomeRegistrationPage registrationPage = new EcoHomeRegistrationPage(driver);
        EcoHomeLoanPage loanPage = new EcoHomeLoanPage(driver);
        EcoHomeInstallerPage installerPage = new EcoHomeInstallerPage(driver);
        
        registrationPage.navigateToEcoHomeHub();
        registrationPage.registerCustomer("Mike", "Johnson", "mike.johnson@example.com", "SecurePass789");
        
        loanPage.applyForLoan("60000", "80000");
        
        installerPage.searchForInstaller("Boston, MA");
        
        boolean areInstallersDisplayed = installerPage.areInstallersDisplayed();
        Assert.assertTrue(areInstallersDisplayed, "Installers should be displayed");
    }
}