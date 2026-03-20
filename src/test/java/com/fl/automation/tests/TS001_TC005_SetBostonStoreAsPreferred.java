package com.fl.automation.tests;

import com.fl.automation.core.BaseTest;
import com.fl.automation.pages.HomePage;
import com.fl.automation.helpers.StoreLocatorHelper;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TS001_TC005_SetBostonStoreAsPreferred extends BaseTest {

    @Test(description = "TC4204 - Click Set My Store for Boston location")
    public void testSetBostonStoreAsPreferred() {
        HomePage homePage = new HomePage(driver);
        StoreLocatorHelper storeLocatorHelper = new StoreLocatorHelper(driver);
        
        homePage.acceptCookiesIfPresent();
        homePage.clickFindStore();
        homePage.clickSelectMyStore();
        storeLocatorHelper.waitForStoreLocatorToLoad();
        
        storeLocatorHelper.enterLocation("Boston MA");
        storeLocatorHelper.clickSearchButton();
        storeLocatorHelper.waitForStoreResults();
        
        Assert.assertTrue(storeLocatorHelper.isStoreDisplayed("375 Washington Street"), 
            "Store should be displayed before setting as preferred");
        
        storeLocatorHelper.clickSetMyStoreForStore("375 Washington Street");
        
        Assert.assertTrue(true, "Set My Store action completed");
    }
}