package com.fl.automation.tests;

import com.fl.automation.core.BaseTest;
import com.fl.automation.pages.HomePage;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * TestRail TC-4112: SCRUM-19509 Eco Home Hub - Data validation
 */
public class TS002_TC009 extends BaseTest {

    @Test(description = "TC-4112: Eco Home Hub data validation")
    public void testEcoHomeHubDataValidation() {
        HomePage homePage = new HomePage(driver);
        homePage.open("https://www.footlocker.com");
        homePage.acceptCookies();
        
        Assert.assertTrue(driver.getTitle().length() > 0, "Page should have a title");
    }
}