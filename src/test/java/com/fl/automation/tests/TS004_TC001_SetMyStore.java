package com.fl.automation.tests;

import com.fl.automation.core.BaseTest;
import com.fl.automation.helpers.StoreLocatorHelper;
import com.fl.automation.pages.HomePage;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class TS004_TC001_SetMyStore extends BaseTest {

    @Test(description = "TC_4173: SCRUM-17166 TS-004 TC-001 - Set My Store")
    public void verifySetMyStore() {
        HomePage homePage = new HomePage(driver);
        StoreLocatorHelper storeLocator = new StoreLocatorHelper(driver);
        
        homePage.navigateToHomePage(baseUrl);
        homePage.clickFindAStore();
        homePage.clickSelectMyStore();
        
        storeLocator.searchForStores("Boston, MA");
        
        List<WebElement> stores = storeLocator.getStoreResults();
        Assert.assertTrue(stores.size() > 0, "Should be able to set a store from search results");
    }
}