package com.fl.automation.tests;

import com.fl.automation.core.BaseTest;
import com.fl.automation.helpers.StoreLocatorHelper;
import com.fl.automation.pages.HomePage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TS001_TC001_FindAStoreCompleteFlow extends BaseTest {

    @Test(description = "TC_4170: SCRUM-17166 TS-001 TC-001 - Find a Store complete flow")
    public void verifyFindAStoreCompleteFlow() {
        HomePage homePage = new HomePage(driver);
        StoreLocatorHelper storeLocator = new StoreLocatorHelper(driver);
        
        homePage.navigateToHomePage(baseUrl);
        Assert.assertTrue(homePage.isFindAStoreDisplayed(), "Find a Store should be displayed");
        
        homePage.clickFindAStore();
        Assert.assertTrue(homePage.isSelectMyStoreDisplayed(), "Select My Store should be displayed");
        
        homePage.clickSelectMyStore();
        Assert.assertTrue(storeLocator.isLocationInputDisplayed(), "Location input should be displayed");
    }
}