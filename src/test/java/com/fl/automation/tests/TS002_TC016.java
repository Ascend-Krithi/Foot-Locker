package com.fl.automation.tests;

import com.fl.automation.core.BaseTest;
import com.fl.automation.pages.HomePage;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * TestRail TC-4119: SCRUM-19509 Eco Home Hub - Performance validation
 */
public class TS002_TC016 extends BaseTest {

    @Test(description = "TC-4119: Eco Home Hub performance validation")
    public void testEcoHomeHubPerformance() {
        HomePage homePage = new HomePage(driver);
        long startTime = System.currentTimeMillis();
        homePage.open("https://www.footlocker.com");
        homePage.acceptCookies();
        long endTime = System.currentTimeMillis();
        
        Assert.assertTrue((endTime - startTime) < 30000, "Page should load within 30 seconds");
    }
}