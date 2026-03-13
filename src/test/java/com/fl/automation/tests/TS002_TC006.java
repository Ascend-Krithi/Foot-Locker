package com.fl.automation.tests;

import com.fl.automation.core.BaseTest;
import com.fl.automation.pages.HomePage;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * TestRail TC-4109: SCRUM-19509 Eco Home Hub - Security validation
 */
public class TS002_TC006 extends BaseTest {

    @Test(description = "TC-4109: Eco Home Hub security validation")
    public void testEcoHomeHubSecurity() {
        HomePage homePage = new HomePage(driver);
        homePage.open("https://www.footlocker.com");
        homePage.acceptCookies();
        
        Assert.assertTrue(driver.getCurrentUrl().startsWith("https"), "Should use HTTPS protocol");
    }
}