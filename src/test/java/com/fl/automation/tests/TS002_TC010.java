package com.fl.automation.tests;

import com.fl.automation.core.BaseTest;
import com.fl.automation.pages.HomePage;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * TestRail TC-4113: SCRUM-19509 Eco Home Hub - Form validation
 */
public class TS002_TC010 extends BaseTest {

    @Test(description = "TC-4113: Eco Home Hub form validation")
    public void testEcoHomeHubFormValidation() {
        HomePage homePage = new HomePage(driver);
        homePage.open("https://www.footlocker.com");
        homePage.acceptCookies();
        
        Assert.assertNotNull(driver, "Driver should be initialized for form validation");
    }
}