package com.fl.automation.tests;

import com.fl.automation.core.BaseTest;
import com.fl.automation.pages.HomePage;
import com.fl.automation.helpers.StoreLocatorHelper;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TS001_TC007_VerifyStorePersistence extends BaseTest {

    @Test(description = "TC4206 - Verify store persists across pages")
    public void testVerifyStorePersistence() {
        HomePage homePage = new HomePage(driver);
        StoreLocatorHelper storeLocatorHelper = new StoreLocatorHelper(driver);
        
        homePage.acceptCookiesIfPresent();
        homePage.clickFindStore();
        homePage.clickSelectMyStore();
        storeLocatorHelper.waitForStoreLocatorToLoad();
        
        storeLocatorHelper.enterLocation("Boston MA");
        storeLocatorHelper.clickSearchButton();
        storeLocatorHelper.waitForStoreResults();
        storeLocatorHelper.clickSetMyStoreForStore("375 Washington Street");
        
        driver.get("https://www.footlocker.com/sneakers");
        
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
        Assert.assertTrue(storeLocatorHelper.isStoreNameInHeader("Washington"), 
            "Selected store should persist and be visible in header on different page");
    }
}