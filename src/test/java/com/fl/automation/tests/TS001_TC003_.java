package com.fl.automation.tests;

import com.fl.automation.core.BaseTest;
import com.fl.automation.pages.HomePage;
import com.fl.automation.helpers.StoreLocatorHelper;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TS001_TC003_ extends BaseTest {

    @Test
    public void testCase_SCRUM17166_TS001_TC003() {
        HomePage homePage = new HomePage(driver);
        StoreLocatorHelper storeLocatorHelper = new StoreLocatorHelper(driver);

        homePage.navigateToHomePage("https://www.footlocker.com");
        String pageTitle = homePage.getPageTitle();
        Assert.assertTrue(pageTitle.contains("Foot Locker") || pageTitle.contains("Sneakers"),
                "Homepage did not load correctly");

        homePage.clickFindStore();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Assert.assertTrue(homePage.isStorePopupDisplayed() || homePage.isSelectMyStoreLinkVisible(),
                "Popup did not appear");

        homePage.clickSelectMyStore();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Assert.assertTrue(storeLocatorHelper.isStoreLocatorPopupVisible(),
                "Store locator popup window did not open");

        storeLocatorHelper.enterLocation("Boston, MA");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        storeLocatorHelper.clickSearchButton();
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Assert.assertTrue(storeLocatorHelper.areStoreResultsDisplayed(),
                "Search results are not displayed with relevant stores in or near Boston");
    }
}