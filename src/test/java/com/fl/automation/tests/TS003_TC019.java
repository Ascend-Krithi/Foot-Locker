package com.fl.automation.tests;

import com.fl.automation.core.BaseTest;
import com.fl.automation.pages.HomePage;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * TestRail TC-4172: SCRUM-17166 Store Locator - Search validation
 */
public class TS003_TC019 extends BaseTest {

    @Test(description = "TC-4172: Store Locator search validation")
    public void testStoreLocatorSearchValidation() {
        HomePage homePage = new HomePage(driver);
        homePage.open("https://www.footlocker.com");
        homePage.acceptCookies();
        
        Assert.assertTrue(homePage.isFindStoreDisplayed(), "Store locator should be accessible");
    }
}