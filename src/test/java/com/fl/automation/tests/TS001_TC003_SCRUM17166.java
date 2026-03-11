package com.fl.automation.tests;

import com.fl.automation.core.BaseTest;
import com.fl.automation.pages.HomePage;
import com.fl.automation.helpers.StoreLocatorHelper;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TS001_TC003_SCRUM17166 extends BaseTest {

    @Test
    public void testCase_SCRUM17166_TS001_TC003() {
        HomePage homePage = new HomePage(driver);
        StoreLocatorHelper storeLocatorHelper = new StoreLocatorHelper(driver);
        
        homePage.dismissCookieConsent();
        
        Assert.assertTrue(driver.getCurrentUrl().contains("footlocker.com"), "Homepage did not load");
        
        homePage.clickFindStore();
        
        Assert.assertTrue(homePage.isStorePopupDisplayed(), "Popup did not appear");
        
        homePage.clickSelectMyStore();
        
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
        Assert.assertTrue(storeLocatorHelper.isLocationTextboxVisible(), "Store locator popup window did not open");
        
        storeLocatorHelper.enterLocation("Boston, MA");
        
        storeLocatorHelper.clickSearchButton();
        
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
        Assert.assertTrue(storeLocatorHelper.areStoreResultsDisplayed(), "Search results are not displayed with relevant stores in or near Boston");
    }
}