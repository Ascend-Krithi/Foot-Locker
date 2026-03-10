package com.fl.automation.tests;

import com.fl.automation.core.BaseTest;
import com.fl.automation.helpers.StoreLocatorHelper;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class TS001_TC003_ extends BaseTest {

    @Test(description = "Test Case - SCRUM-17166 TS-001 TC-003: Search for stores in Boston, MA and verify results")
    public void testSearchStoresBoston() {
        homePage.clickFindStore();
        homePage.clickSelectMyStore();
        
        StoreLocatorHelper storeLocatorHelper = new StoreLocatorHelper(driver);
        storeLocatorHelper.enterSearchLocation("Boston, MA");
        
        homePage.clickSearchForStores();
        
        List<WebElement> storeResults = storeLocatorHelper.getStoreResultCards();
        Assert.assertFalse(storeResults.isEmpty(), "Search results should be displayed with stores in or near Boston");
        Assert.assertTrue(storeResults.size() > 0, "At least one store should be found in Boston area");
    }
}