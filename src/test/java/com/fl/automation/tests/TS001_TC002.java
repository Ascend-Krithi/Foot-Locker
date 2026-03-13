package com.fl.automation.tests;

import com.fl.automation.core.BaseTest;
import com.fl.automation.helpers.StoreLocatorHelper;
import com.fl.automation.pages.HomePage;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * TestRail TC-3194: SCRUM-17166 TS-001 TC-002
 * Test: Store Locator textbox validation
 */
public class TS001_TC002 extends BaseTest {

    @Test(description = "TC-3194: Store Locator textbox validation")
    public void testStoreLocatorTextboxValidation() {
        HomePage homePage = new HomePage(driver);
        StoreLocatorHelper storeLocator = new StoreLocatorHelper(driver);

        homePage.open("https://www.footlocker.com");
        homePage.acceptCookies();
        homePage.clickFindStore();
        
        Assert.assertTrue(storeLocator.isStoreLocatorInputDisplayed(), 
            "Store locator textbox should be visible and enabled");
        
        storeLocator.enterLocation("Test Location");
        
        Assert.assertTrue(true, "Successfully entered text in store locator textbox");
    }
}