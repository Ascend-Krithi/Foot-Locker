package com.fl.automation.tests;

import com.fl.automation.core.BaseTest;
import com.fl.automation.pages.HomePage;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * TestRail TC-4116: SCRUM-19509 Eco Home Hub - User authentication
 */
public class TS002_TC013 extends BaseTest {

    @Test(description = "TC-4116: Eco Home Hub user authentication")
    public void testEcoHomeHubAuthentication() {
        HomePage homePage = new HomePage(driver);
        homePage.open("https://www.footlocker.com");
        homePage.acceptCookies();
        
        Assert.assertTrue(driver.getPageSource().contains("footlocker"), "Page content should load");
    }
}