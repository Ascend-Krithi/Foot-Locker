package com.fl.automation.tests;

import com.fl.automation.core.BaseTest;
import com.fl.automation.pages.HomePage;
import com.fl.automation.helpers.StoreLocatorHelper;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TS001_TC002_SCRUM17166 extends BaseTest {

    @Test
    public void testCase_SCRUM17166_TS001_TC002() {
        HomePage homePage = new HomePage(driver);
        StoreLocatorHelper storeLocatorHelper = new StoreLocatorHelper(driver);
        
        homePage.dismissCookieConsent();
        
        Assert.assertTrue(driver.getCurrentUrl().contains("footlocker.com"), "Homepage did not load");
        
        homePage.clickFindStore();
        
        Assert.assertTrue(homePage.isStorePopupDisplayed(), "Popup did not appear below 'Find a Store'");
        
        homePage.clickSelectMyStore();
        
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
        Assert.assertTrue(storeLocatorHelper.isLocationTextboxVisible(), "Popup does not contain 'Location' textbox");
        Assert.assertTrue(storeLocatorHelper.isSearchButtonVisible(), "Popup does not contain 'Search for Stores' button");
    }
}