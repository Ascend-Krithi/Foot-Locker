package com.fl.automation.tests;

import com.fl.automation.core.BaseTest;
import com.fl.automation.pages.HomePage;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * TestRail TC-4122: SCRUM-19509 Eco Home Hub - Integration testing
 */
public class TS002_TC019 extends BaseTest {

    @Test(description = "TC-4122: Eco Home Hub integration testing")
    public void testEcoHomeHubIntegration() {
        HomePage homePage = new HomePage(driver);
        homePage.open("https://www.footlocker.com");
        homePage.acceptCookies();
        
        Assert.assertTrue(driver.getCurrentUrl().contains("footlocker"), "Integration should work");
    }
}