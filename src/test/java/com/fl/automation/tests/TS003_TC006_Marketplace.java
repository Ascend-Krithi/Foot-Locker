package com.fl.automation.tests;

import org.testng.annotations.Test;
import org.testng.Assert;
import com.fl.automation.core.BaseTest;
import com.fl.automation.pages.EcoHomeHubHomePage;
import com.fl.automation.pages.RegistrationPage;

public class TS003_TC006_Marketplace extends BaseTest {
    
    @Test
    public void testMarketplaceRegistration() {
        driver.get("https://marketplace.example.com/register");
        RegistrationPage regPage = new RegistrationPage(driver);
        regPage.fillRegistrationForm("Jane Smith", "jane@example.com", "Password456", "9876543210", "456 Oak Ave");
        regPage.submitForm();
        
        Assert.assertTrue(regPage.isRegistrationSuccessful(), "Registration should be successful");
    }
}