package com.fl.automation.tests;

import com.fl.automation.core.BaseTest;
import com.fl.automation.pages.HomePage;
import com.fl.automation.pages.StoreLocatorPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TS002_TC001_TestCaseSCRUM15408TS002TC001 extends BaseTest {
    @Test
    public void testSelectMyStorePopup() {
        test = extent.createTest("TS002_TC001_TestCaseSCRUM15408TS002TC001");
        HomePage homePage = new HomePage(driver);
        StoreLocatorPage locatorPage = new StoreLocatorPage(driver);

        test.info("Step 1: Launch the Foot Locker homepage in a browser.");
        homePage.open("https://www.footlocker.com");
        Assert.assertTrue(driver.getTitle().toLowerCase().contains("foot locker"), "Homepage title validation");

        test.info("Step 2: Click on the 'Find a Store' link/button.");
        homePage.clickFindAStore();

        test.info("Step 3: Click on the 'Select My Store' link in the popup.");
        locatorPage.clickSelectMyStore();
        Assert.assertTrue(locatorPage.getSearchInput().isDisplayed(), "Location textbox is visible");
    }
}