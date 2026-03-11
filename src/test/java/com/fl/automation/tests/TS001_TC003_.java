package com.fl.automation.tests;

import com.fl.automation.core.BaseTest;
import com.fl.automation.pages.HomePage;
import com.fl.automation.helpers.StoreLocatorHelper;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TS001_TC003_ extends BaseTest {
    
    @Test(description = "Test Case - SCRUM-17166 TS-001 TC-003")
    public void testSearchForStoresInBostonMA() {
        HomePage homePage = new HomePage(driver);
        StoreLocatorHelper storeLocatorHelper = new StoreLocatorHelper(driver);
        
        Assert.assertTrue(driver.getTitle().contains("Foot Locker"), "Homepage should load");
        
        homePage.clickFindStore();
        
        Assert.assertTrue(homePage.isPopupDisplayed(), "Popup should appear");
        
        homePage.clickSelectMyStore();
        
        Assert.assertTrue(storeLocatorHelper.isLocationTextboxVisible(), "Store locator popup window should open");
        
        storeLocatorHelper.enterLocation("Boston, MA");
        
        storeLocatorHelper.clickSearchButton();
        
        Assert.assertTrue(storeLocatorHelper.areStoreResultsDisplayed(), "Search results should be displayed with relevant stores in or near Boston");
        
        int storeCount = storeLocatorHelper.getStoreResultCount();
        Assert.assertTrue(storeCount > 0, "At least one store should be found in Boston, MA area. Found: " + storeCount);
    }
}
