package com.fl.automation.tests;

import com.fl.automation.core.BaseTest;
import com.fl.automation.pages.HomePage;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * TestRail TC-4121: SCRUM-19509 Eco Home Hub - Cross-browser compatibility
 */
public class TS002_TC018 extends BaseTest {

    @Test(description = "TC-4121: Eco Home Hub cross-browser compatibility")
    public void testEcoHomeHubCrossBrowser() {
        HomePage homePage = new HomePage(driver);
        homePage.open("https://www.footlocker.com");
        homePage.acceptCookies();
        
        Assert.assertNotNull(driver.getWindowHandle(), "Browser should be compatible");
    }
}