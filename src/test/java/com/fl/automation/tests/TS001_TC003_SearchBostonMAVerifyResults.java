package com.fl.automation.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import com.fl.automation.core.BaseTest;

public class TS001_TC003_SearchBostonMAVerifyResults extends BaseTest {
    
    @Test(description = "TC_4202: Open Find a Store popup, enter Boston MA, click Search, verify results")
    public void testSearchBostonMAVerifyResults() {
        homePage.acceptCookiesIfPresent();
        homePage.clickFindStore();
        homePage.clickSelectMyStore();
        storeLocatorHelper.waitForStoreLocatorToLoad();
        storeLocatorHelper.enterLocation("Boston MA");
        storeLocatorHelper.clickSearchForStores();
        Assert.assertTrue(storeLocatorHelper.areStoreResultsDisplayed(), "Store results should be displayed after searching for Boston MA");
    }
}