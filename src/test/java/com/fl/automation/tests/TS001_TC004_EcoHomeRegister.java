package com.fl.automation.tests;

import com.fl.automation.core.BaseTest;
import com.fl.automation.pages.EcoHomeRegistrationPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TS001_TC004_EcoHomeRegister extends BaseTest {

    @Test(description = "TC_4104: SCRUM-19509 TS-001 TC-001 - Register customer on Eco Home Hub")
    public void testEcoHomeRegister() {
        EcoHomeRegistrationPage registrationPage = new EcoHomeRegistrationPage(driver);
        
        registrationPage.navigateToEcoHomeHub();
        registrationPage.registerCustomer("John", "Doe", "john.doe@example.com", "SecurePass123");
        
        boolean isRegistrationSuccessful = registrationPage.isRegistrationSuccessful();
        Assert.assertTrue(isRegistrationSuccessful, "Registration should be successful");
    }
}