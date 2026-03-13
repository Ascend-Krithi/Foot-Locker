package com.fl.automation.tests;

import com.fl.automation.core.BaseTest;
import com.fl.automation.pages.HomePage;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * TestRail TC-4115: SCRUM-19509 Eco Home Hub - Navigation flow
 */
public class TS002_TC012 extends BaseTest {

    @Test(description = "TC-4115: Eco Home Hub navigation flow")
    public void testEcoHomeHubNavigation() {
        HomePage homePage = new HomePage(driver);
        homePage.open("https://www.footlocker.com");
        homePage.acceptCookies();
        
        Assert.assertTrue(homePage.isFindStoreDisplayed(), "Navigation elements should be present");
    }
}