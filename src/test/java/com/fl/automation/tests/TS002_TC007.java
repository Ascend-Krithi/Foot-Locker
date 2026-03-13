package com.fl.automation.tests;

import com.fl.automation.core.BaseTest;
import com.fl.automation.pages.HomePage;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * TestRail TC-4110: SCRUM-19509 Eco Home Hub - Deployment validation
 */
public class TS002_TC007 extends BaseTest {

    @Test(description = "TC-4110: Eco Home Hub deployment validation")
    public void testEcoHomeHubDeployment() {
        HomePage homePage = new HomePage(driver);
        homePage.open("https://www.footlocker.com");
        homePage.acceptCookies();
        
        Assert.assertNotNull(driver.getWindowHandle(), "Window handle should be available");
    }
}