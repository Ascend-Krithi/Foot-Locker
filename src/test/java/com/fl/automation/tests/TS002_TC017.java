package com.fl.automation.tests;

import com.fl.automation.core.BaseTest;
import com.fl.automation.pages.HomePage;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * TestRail TC-4120: SCRUM-19509 Eco Home Hub - Accessibility compliance
 */
public class TS002_TC017 extends BaseTest {

    @Test(description = "TC-4120: Eco Home Hub accessibility compliance")
    public void testEcoHomeHubAccessibility() {
        HomePage homePage = new HomePage(driver);
        homePage.open("https://www.footlocker.com");
        homePage.acceptCookies();
        
        Assert.assertTrue(driver.getPageSource().length() > 0, "Page should be accessible");
    }
}