package com.fl.automation.tests;

import com.fl.automation.core.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TS001_TC004 extends BaseTest {

    @Test(description = "TC4104: SCRUM-19509 TS-001 TC-001 - Eco Home Hub customer registration")
    public void testEcoHomeHubCustomerRegistration() {
        driver.get("https://ecohomehub.example.com/register");
        
        Assert.assertTrue(driver.getCurrentUrl().contains("register"), "Should navigate to registration page");
        Assert.assertTrue(driver.getTitle().contains("Register") || driver.getTitle().contains("Sign Up"), 
            "Page title should indicate registration");
    }
}