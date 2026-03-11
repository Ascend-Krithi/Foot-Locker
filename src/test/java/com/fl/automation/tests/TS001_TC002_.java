package com.fl.automation.tests;

import com.fl.automation.core.BaseTest;
import com.fl.automation.pages.HomePage;
import com.fl.automation.helpers.StoreLocatorHelper;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TS001_TC002_ extends BaseTest {
    
    @Test(description = "Test Case - SCRUM-17166 TS-001 TC-002")
    public void testStoreLocatorPopupContainsLocationAndSearchButton() {
        HomePage homePage = new HomePage(driver);
        StoreLocatorHelper storeLocatorHelper = new StoreLocatorHelper(driver);
        
        Assert.assertTrue(driver.getTitle().contains("Foot Locker"), "Homepage should load");
        
        homePage.clickFindStore();
        
        Assert.assertTrue(homePage.isPopupDisplayed(), "Popup should appear below 'Find a Store'");
        
        homePage.clickSelectMyStore();
        
        Assert.assertTrue(storeLocatorHelper.isLocationTextboxVisible(), "'Location' textbox should be present in store locator popup");
        
        Assert.assertTrue(storeLocatorHelper.isSearchButtonVisible(), "'Search for Stores' button should be present in store locator popup");
    }
}
