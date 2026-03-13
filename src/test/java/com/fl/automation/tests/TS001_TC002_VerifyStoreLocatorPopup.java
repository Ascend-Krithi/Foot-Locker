package com.fl.automation.tests;

import com.aventstack.extentreports.Status;
import com.fl.automation.core.BaseTest;
import com.fl.automation.helpers.StoreLocatorHelper;
import com.fl.automation.pages.HomePage;
import com.fl.automation.utils.ExtentManager;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TS001_TC002_VerifyStoreLocatorPopup extends BaseTest {
    
    @Test(description = "TC3194: SCRUM-17166 TS-001 TC-002 - Verify Store Locator popup with Location textbox and Search button")
    public void verifyStoreLocatorPopup() {
        ExtentManager.getTest().log(Status.INFO, "Starting test: Verify Store Locator popup with Location textbox and Search button");
        
        HomePage homePage = new HomePage(driver);
        StoreLocatorHelper storeLocator = new StoreLocatorHelper(driver);
        
        ExtentManager.getTest().log(Status.INFO, "Navigating to Foot Locker homepage");
        homePage.navigateToHomePage(BASE_URL);
        ExtentManager.getTest().log(Status.PASS, "Home page loaded");
        
        ExtentManager.getTest().log(Status.INFO, "Opening Store Locator popup");
        storeLocator.clickFindAStore();
        ExtentManager.getTest().log(Status.PASS, "Store Locator popup opened");
        
        ExtentManager.getTest().log(Status.INFO, "Verifying Location textbox is displayed");
        Assert.assertTrue(storeLocator.isStoreLocatorPopupDisplayed(), "Location textbox should be displayed");
        ExtentManager.getTest().log(Status.PASS, "Location textbox verified");
        
        ExtentManager.getTest().log(Status.INFO, "Verifying Search button is displayed");
        Assert.assertTrue(storeLocator.isSearchButtonDisplayed(), "Search button should be displayed");
        ExtentManager.getTest().log(Status.PASS, "Search button verified");
        
        ExtentManager.getTest().log(Status.INFO, "Test completed successfully");
    }
}