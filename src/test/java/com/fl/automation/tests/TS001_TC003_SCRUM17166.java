package com.fl.automation.tests;

import org.testng.annotations.Test;
import org.testng.Assert;
import com.fl.automation.core.BaseTest;
import com.fl.automation.pages.HomePage;
import com.fl.automation.helpers.StoreLocatorHelper;

public class TS001_TC003_SCRUM17166 extends BaseTest {
    
    @Test
    public void testStoreSearchBostonMA() {
        driver.get("https://www.footlocker.com");
        HomePage homePage = new HomePage(driver);
        homePage.clickFindStore();
        
        StoreLocatorHelper storeHelper = new StoreLocatorHelper(driver);
        storeHelper.searchStore("Boston, MA");
        
        Assert.assertTrue(driver.getCurrentUrl().contains("store") || driver.getPageSource().contains("Boston"), "Store search results should be displayed");
    }
}