package com.fl.automation.tests;

import com.aventstack.extentreports.Status;
import com.fl.automation.core.BaseTest;
import com.fl.automation.helpers.StoreLocatorHelper;
import com.fl.automation.pages.HomePage;
import com.fl.automation.utils.ExtentManager;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TS001_TC001_VerifyFindAStorePopup extends BaseTest {
    
    @Test(description = "TC3193: SCRUM-17166 TS-001 TC-001 - Verify Find a Store popup and Select My Store link")
    public void verifyFindAStorePopup() {
        ExtentManager.getTest().log(Status.INFO, "Starting test: Verify Find a Store popup and Select My Store link");
        
        HomePage homePage = new HomePage(driver);
        StoreLocatorHelper storeLocator = new StoreLocatorHelper(driver);
        
        ExtentManager.getTest().log(Status.INFO, "Navigating to Foot Locker homepage");
        homePage.navigateToHomePage(BASE_URL);
        Assert.assertTrue(homePage.isHomePageLoaded(), "Home page should be loaded");
        ExtentManager.getTest().log(Status.PASS, "Home page loaded successfully");
        
        ExtentManager.getTest().log(Status.INFO, "Clicking on Find a Store link");
        storeLocator.clickFindAStore();
        ExtentManager.getTest().log(Status.PASS, "Find a Store link clicked");
        
        ExtentManager.getTest().log(Status.INFO, "Verifying Store Locator popup is displayed");
        Assert.assertTrue(storeLocator.isStoreLocatorPopupDisplayed(), "Store Locator popup should be displayed");
        ExtentManager.getTest().log(Status.PASS, "Store Locator popup displayed successfully");
        
        ExtentManager.getTest().log(Status.INFO, "Test completed successfully");
    }
}