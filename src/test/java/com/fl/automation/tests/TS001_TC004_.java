package com.fl.automation.tests;

import com.fl.automation.core.BaseTest;
import com.fl.automation.helpers.StoreLocatorHelper;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TS001_TC004_ extends BaseTest {

    @Test(description = "Test Case - SCRUM-17166 TS-001 TC-004: Verify specific store address in Boston search results")
    public void testVerifySpecificBostonStoreAddress() {
        homePage.clickFindStore();
        homePage.clickSelectMyStore();
        
        StoreLocatorHelper storeLocatorHelper = new StoreLocatorHelper(driver);
        storeLocatorHelper.enterSearchLocation("Boston, MA");
        
        homePage.clickSearchForStores();
        
        String targetAddress = "375 Washington Street";
        WebElement targetStore = storeLocatorHelper.findStoreByAddress(targetAddress);
        
        Assert.assertNotNull(targetStore, "Store with address '375 Washington Street, Boston, MA 02108' should be visible in search results");
    }
}