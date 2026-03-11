package com.fl.automation.tests;

import com.fl.automation.core.BaseTest;
import com.fl.automation.helpers.StoreLocatorHelper;
import com.fl.automation.pages.HomePage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TS001_TC003_ extends BaseTest {

    @Test(description = "Test Case - SCRUM-17166 TS-001 TC-003")
    public void testStoreSearchForBostonMA() {
        driver.get("https://www.footlocker.com");

        HomePage homePage = new HomePage(driver);
        StoreLocatorHelper storeLocatorHelper = new StoreLocatorHelper(driver);

        homePage.dismissCookieConsent();

        Assert.assertTrue(driver.getTitle().contains("Foot Locker"), "Homepage did not load correctly");

        homePage.clickFindStore();

        Assert.assertTrue(homePage.isStorePopupDisplayed(), "Store popup did not appear");

        homePage.clickSelectMyStore();

        storeLocatorHelper.enterLocation("Boston, MA");

        storeLocatorHelper.clickSearchButton();

        Assert.assertTrue(storeLocatorHelper.areStoreResultsDisplayed(), "Store search results are not displayed");
    }
}
