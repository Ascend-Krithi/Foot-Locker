package com.fl.automation.tests;

import com.fl.automation.core.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TS001_TC002_SCRUM17166 extends BaseTest {

    @Test(description = "TC 3194 - Verify store locator popup elements")
    public void testStoreLocatorPopupElements() {
        homePage.acceptCookiesIfPresent();
        homePage.closeModalIfPresent();
        
        homePage.clickFindAStore();
        
        storeLocatorHelper.clickSelectMyStore();
        
        boolean isLocationInputVisible = storeLocatorHelper.isLocationInputVisible();
        Assert.assertTrue(isLocationInputVisible, "Location textbox should be visible in store locator popup");
        
        boolean isSearchButtonVisible = storeLocatorHelper.isSearchButtonVisible();
        Assert.assertTrue(isSearchButtonVisible, "Search for Stores button should be visible in store locator popup");
    }
}