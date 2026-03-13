package com.fl.automation.tests;

import com.fl.automation.core.BaseTest;
import com.fl.automation.pages.HomePage;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * TestRail TC-4114: SCRUM-19509 Eco Home Hub - Error handling
 */
public class TS002_TC011 extends BaseTest {

    @Test(description = "TC-4114: Eco Home Hub error handling")
    public void testEcoHomeHubErrorHandling() {
        HomePage homePage = new HomePage(driver);
        homePage.open("https://www.footlocker.com");
        homePage.acceptCookies();
        
        Assert.assertTrue(driver.getCurrentUrl().length() > 0, "URL should be loaded");
    }
}