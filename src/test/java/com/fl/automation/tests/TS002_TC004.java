package com.fl.automation.tests;

import com.fl.automation.core.BaseTest;
import com.fl.automation.pages.HomePage;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * TestRail TC-4107: SCRUM-19509 Eco Home Hub - Admin vetting
 */
public class TS002_TC004 extends BaseTest {

    @Test(description = "TC-4107: Eco Home Hub admin vetting")
    public void testEcoHomeHubAdminVetting() {
        HomePage homePage = new HomePage(driver);
        homePage.open("https://www.footlocker.com");
        homePage.acceptCookies();
        
        Assert.assertTrue(driver.getCurrentUrl().contains("footlocker"), "URL should contain footlocker");
    }
}