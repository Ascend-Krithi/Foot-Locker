package com.fl.automation.tests;

import com.aventstack.extentreports.Status;
import com.fl.automation.core.BaseTest;
import com.fl.automation.helpers.StoreLocatorHelper;
import com.fl.automation.pages.HomePage;
import com.fl.automation.utils.ExtentManager;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TS024_TC001_VerifyFindAStorePopupWorkflow extends BaseTest {
    
    @Test(description = "TC4170: SCRUM-17166 TS-001 TC-001 - Verify Find a Store popup workflow")
    public void verifyFindAStorePopupWorkflow() {
        ExtentManager.getTest().log(Status.INFO, "Starting test: Verify Find a Store popup workflow");
        
        HomePage homePage = new HomePage(driver);
        StoreLocatorHelper storeLocator = new StoreLocatorHelper(driver);
        
        ExtentManager.getTest().log(Status.INFO, "Navigating to Foot Locker homepage");
        homePage.navigateToHomePage(BASE_URL);
        Assert.assertTrue(homePage.isHomePageLoaded(), "Home page should be loaded");
        ExtentManager.getTest().log(Status.PASS, "Home page loaded successfully");
        
        ExtentManager.getTest().log(Status.INFO, "Opening Find a Store popup");
        storeLocator.clickFindAStore();
        ExtentManager.getTest().log(Status.PASS, "Find a Store popup opened");
        
        ExtentManager.getTest().log(Status.INFO, "Verifying popup elements");
        Assert.assertTrue(storeLocator.isStoreLocatorPopupDisplayed(), "Store locator popup should be displayed");
        Assert.assertTrue(storeLocator.isSearchButtonDisplayed(), "Search button should be displayed");
        ExtentManager.getTest().log(Status.PASS, "All popup elements verified");
        
        ExtentManager.getTest().log(Status.INFO, "Test completed successfully");
    }
}