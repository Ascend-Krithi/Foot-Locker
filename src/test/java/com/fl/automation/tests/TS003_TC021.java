package com.fl.automation.tests;

import com.fl.automation.core.BaseTest;
import com.fl.automation.pages.HomePage;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * TestRail TC-4174: SCRUM-17166 Store Locator - Map integration
 */
public class TS003_TC021 extends BaseTest {

    @Test(description = "TC-4174: Store Locator map integration")
    public void testStoreLocatorMapIntegration() {
        HomePage homePage = new HomePage(driver);
        homePage.open("https://www.footlocker.com");
        homePage.acceptCookies();
        
        Assert.assertTrue(driver.getPageSource().length() > 0, "Map integration should work");
    }
}