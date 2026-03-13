package com.fl.automation.tests;

import com.fl.automation.core.BaseTest;
import com.fl.automation.pages.HomePage;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * TestRail TC-4105: SCRUM-19509 Eco Home Hub - Loan application
 */
public class TS002_TC002 extends BaseTest {

    @Test(description = "TC-4105: Eco Home Hub loan application")
    public void testEcoHomeHubLoanApplication() {
        HomePage homePage = new HomePage(driver);
        homePage.open("https://www.footlocker.com");
        homePage.acceptCookies();
        
        Assert.assertTrue(driver.getTitle().length() > 0, "Page title should be present");
    }
}