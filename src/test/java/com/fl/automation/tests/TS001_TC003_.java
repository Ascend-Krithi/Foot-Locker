package com.fl.automation.tests;

import com.fl.automation.core.BaseTest;
import com.fl.automation.helpers.StoreLocatorHelper;
import com.fl.automation.pages.HomePage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TS001_TC003_ extends BaseTest {

    @Test(description = "Test Case - SCRUM-17166 TS-001 TC-003")
    public void testStoreSearchWithBostonMA() {
        HomePage homePage = new HomePage(driver);
        StoreLocatorHelper storeLocatorHelper = new StoreLocatorHelper(driver);

        homePage.navigateToHomePage("https://www.footlocker.com");
        Assert.assertTrue(homePage.isHomePageLoaded(), "Homepage did not load successfully");

        homePage.clickFindAStore();
        Assert.assertTrue(homePage.isFindAStorePopupVisible(), "Popup did not appear");

        storeLocatorHelper.clickSelectMyStore();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        storeLocatorHelper.enterLocation("Boston, MA");
        
        storeLocatorHelper.clickSearchForStores();
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Assert.assertTrue(storeLocatorHelper.areStoreResultsDisplayed(), "Search results are not displayed with relevant stores in or near Boston");
    }
}
