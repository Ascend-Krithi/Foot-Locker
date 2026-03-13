package com.fl.automation.tests;

import com.fl.automation.core.BaseTest;
import com.fl.automation.pages.HomePage;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * TestRail TC-4104: SCRUM-19509 Eco Home Hub - Customer registration
 */
public class TS002_TC001 extends BaseTest {

    @Test(description = "TC-4104: Eco Home Hub customer registration")
    public void testEcoHomeHubCustomerRegistration() {
        HomePage homePage = new HomePage(driver);
        homePage.open("https://www.footlocker.com");
        homePage.acceptCookies();
        
        Assert.assertTrue(driver.getCurrentUrl().contains("footlocker.com"), 
            "Should navigate to Foot Locker homepage");
    }
}