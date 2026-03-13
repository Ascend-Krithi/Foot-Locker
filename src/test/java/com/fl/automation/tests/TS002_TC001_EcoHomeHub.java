package com.fl.automation.tests;

import org.testng.annotations.Test;
import org.testng.Assert;
import com.fl.automation.core.BaseTest;
import com.fl.automation.pages.EcoHomeHubHomePage;
import com.fl.automation.pages.RegistrationPage;

public class TS002_TC001_EcoHomeHub extends BaseTest {
    
    @Test
    public void testCustomerRegistration() {
        driver.get("https://ecohomehub.example.com");
        EcoHomeHubHomePage homePage = new EcoHomeHubHomePage(driver);
        homePage.clickRegister();
        
        RegistrationPage regPage = new RegistrationPage(driver);
        regPage.fillRegistrationForm("John Doe", "john@example.com", "Password123", "1234567890", "123 Main St");
        regPage.submitForm();
        
        Assert.assertTrue(regPage.isRegistrationSuccessful(), "Registration should be successful");
    }
}