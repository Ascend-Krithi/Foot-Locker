package com.fl.automation.tests;

import com.fl.automation.core.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TS001_TC003_SCRUM17166 extends BaseTest {

    @Test(description = "TC 3195 - Verify store search functionality with Boston, MA")
    public void testStoreSearchWithBostonMA() {
        homePage.acceptCookiesIfPresent();
        homePage.closeModalIfPresent();
        
        homePage.clickFindAStore();
        
        storeLocatorHelper.clickSelectMyStore();
        
        storeLocatorHelper.enterLocation("Boston, MA");
        
        storeLocatorHelper.clickSearchButton();
        
        boolean areResultsDisplayed = storeLocatorHelper.areStoreResultsDisplayed();
        Assert.assertTrue(areResultsDisplayed, "Store search results should be displayed for Boston, MA");
    }
}