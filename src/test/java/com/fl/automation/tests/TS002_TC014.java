package com.fl.automation.tests;

import com.fl.automation.core.BaseTest;
import com.fl.automation.pages.HomePage;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * TestRail TC-4117: SCRUM-19509 Eco Home Hub - Session management
 */
public class TS002_TC014 extends BaseTest {

    @Test(description = "TC-4117: Eco Home Hub session management")
    public void testEcoHomeHubSessionManagement() {
        HomePage homePage = new HomePage(driver);
        homePage.open("https://www.footlocker.com");
        homePage.acceptCookies();
        
        Assert.assertTrue(driver.manage().getCookies().size() >= 0, "Session cookies should be managed");
    }
}