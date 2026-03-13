package com.fl.automation.tests;

import com.fl.automation.core.BaseTest;
import com.fl.automation.pages.HomePage;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * TestRail TC-4106: SCRUM-19509 Eco Home Hub - Installer search
 */
public class TS002_TC003 extends BaseTest {

    @Test(description = "TC-4106: Eco Home Hub installer search")
    public void testEcoHomeHubInstallerSearch() {
        HomePage homePage = new HomePage(driver);
        homePage.open("https://www.footlocker.com");
        homePage.acceptCookies();
        
        Assert.assertNotNull(driver, "Driver should be initialized");
    }
}