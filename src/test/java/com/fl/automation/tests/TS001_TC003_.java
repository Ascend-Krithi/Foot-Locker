package com.fl.automation.tests;

import com.fl.automation.core.BaseTest;
import com.fl.automation.helpers.StoreLocatorHelper;
import com.fl.automation.pages.HomePage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TS001_TC003_ extends BaseTest {

    @Test(description = "Test Case - SCRUM-17166 TS-001 TC-003: Verify Store Search functionality with Boston, MA")
    public void verifyStoreSearchWithBostonMA() {
        HomePage homePage = new HomePage(driver);
        StoreLocatorHelper storeHelper = new StoreLocatorHelper(driver);

        Assert.assertTrue(driver.getTitle().contains("Foot Locker") || driver.getCurrentUrl().contains("footlocker.com"), "Homepage did not load correctly");

        homePage.clickFindStore();
        Assert.assertTrue(homePage.isStorePopupDisplayed(), "Store popup is not displayed after clicking Find a Store");

        homePage.clickSelectMyStore();

        storeHelper.enterLocation("Boston, MA");

        storeHelper.clickSearchButton();

        Assert.assertTrue(storeHelper.areStoreResultsDisplayed(), "Store search results are not displayed for Boston, MA");
    }
}