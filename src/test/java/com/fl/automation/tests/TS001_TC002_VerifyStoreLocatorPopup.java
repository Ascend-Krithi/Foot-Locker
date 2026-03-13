package com.fl.automation.tests;

import com.fl.automation.core.BaseTest;
import com.fl.automation.helpers.StoreLocatorHelper;
import com.fl.automation.pages.HomePage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TS001_TC002_VerifyStoreLocatorPopup extends BaseTest {

    @Test(description = "TC_3194: SCRUM-17166 TS-001 TC-002 - Verify Store locator popup with Location textbox and Search button")
    public void verifyStoreLocatorPopup() {
        HomePage homePage = new HomePage(driver);
        StoreLocatorHelper storeLocator = new StoreLocatorHelper(driver);
        
        homePage.navigateToHomePage(baseUrl);
        homePage.clickFindAStore();
        homePage.clickSelectMyStore();
        
        Assert.assertTrue(storeLocator.isLocationInputDisplayed(), "Location input should be displayed");
        Assert.assertTrue(storeLocator.isSearchButtonDisplayed(), "Search button should be displayed");
    }
}