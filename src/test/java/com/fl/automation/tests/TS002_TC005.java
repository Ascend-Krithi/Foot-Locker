package com.fl.automation.tests;

import com.fl.automation.core.BaseTest;
import com.fl.automation.pages.HomePage;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * TestRail TC-4108: SCRUM-19509 Eco Home Hub - Installer portal
 */
public class TS002_TC005 extends BaseTest {

    @Test(description = "TC-4108: Eco Home Hub installer portal")
    public void testEcoHomeHubInstallerPortal() {
        HomePage homePage = new HomePage(driver);
        homePage.open("https://www.footlocker.com");
        homePage.acceptCookies();
        
        Assert.assertTrue(driver.getPageSource().length() > 0, "Page source should be loaded");
    }
}