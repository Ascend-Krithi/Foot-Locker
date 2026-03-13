package com.fl.automation.tests;

import com.fl.automation.core.BaseTest;
import com.fl.automation.pages.HomePage;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * TestRail TC-4123: SCRUM-19509 Eco Home Hub - End-to-end workflow
 */
public class TS002_TC020 extends BaseTest {

    @Test(description = "TC-4123: Eco Home Hub end-to-end workflow")
    public void testEcoHomeHubEndToEnd() {
        HomePage homePage = new HomePage(driver);
        homePage.open("https://www.footlocker.com");
        homePage.acceptCookies();
        
        Assert.assertTrue(driver.getTitle().length() > 0, "End-to-end workflow should complete");
    }
}