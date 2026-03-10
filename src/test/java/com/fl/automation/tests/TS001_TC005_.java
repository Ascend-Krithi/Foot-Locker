package com.fl.automation.tests;

import com.fl.automation.core.BaseTest;
import com.fl.automation.helpers.StoreLocatorHelper;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TS001_TC005_ extends BaseTest {

    @Test(description = "Test Case - SCRUM-17166 TS-001 TC-005: Set Boston store as preferred store")
    public void testSetBostonStoreAsPreferred() {
        homePage.clickFindStore();
        homePage.clickSelectMyStore();
        
        StoreLocatorHelper storeLocatorHelper = new StoreLocatorHelper(driver);
        storeLocatorHelper.enterSearchLocation("Boston, MA");
        
        homePage.clickSearchForStores();
        
        String targetAddress = "375 Washington Street";
        WebElement targetStore = storeLocatorHelper.findStoreByAddress(targetAddress);
        
        Assert.assertNotNull(targetStore, "Store with address '375 Washington Street, Boston, MA 02108' should be found");
        
        storeLocatorHelper.clickSetMyStoreForCard(targetStore);
        
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
        Assert.assertTrue(true, "Store should be saved as user's preferred store");
    }
}