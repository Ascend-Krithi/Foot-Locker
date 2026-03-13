package com.fl.automation.tests;

import com.aventstack.extentreports.Status;
import com.fl.automation.core.BaseTest;
import com.fl.automation.helpers.StoreLocatorHelper;
import com.fl.automation.pages.HomePage;
import com.fl.automation.utils.ExtentManager;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TS026_TC001_VerifySpecificStoreAddress extends BaseTest {
    
    @Test(description = "TC4172: SCRUM-17166 TS-003 TC-001 - Verify specific store address in results")
    public void verifySpecificStoreAddress() {
        ExtentManager.getTest().log(Status.INFO, "Starting test: Verify specific store address in results");
        
        HomePage homePage = new HomePage(driver);
        StoreLocatorHelper storeLocator = new StoreLocatorHelper(driver);
        
        ExtentManager.getTest().log(Status.INFO, "Navigating to Foot Locker homepage");
        homePage.navigateToHomePage(BASE_URL);
        ExtentManager.getTest().log(Status.PASS, "Home page loaded");
        
        ExtentManager.getTest().log(Status.INFO, "Opening Store Locator");
        storeLocator.clickFindAStore();
        ExtentManager.getTest().log(Status.PASS, "Store Locator opened");
        
        ExtentManager.getTest().log(Status.INFO, "Searching for stores in Boston, MA");
        storeLocator.enterLocation("Boston, MA");
        storeLocator.clickSearchButton();
        ExtentManager.getTest().log(Status.PASS, "Search executed");
        
        ExtentManager.getTest().log(Status.INFO, "Verifying specific store address in results");
        String expectedAddress = "Boston";
        boolean addressFound = storeLocator.verifyStoreInResults(expectedAddress);
        Assert.assertTrue(addressFound, "Store with address containing '" + expectedAddress + "' should be in results");
        ExtentManager.getTest().log(Status.PASS, "Specific store address verified in results");
        
        ExtentManager.getTest().log(Status.INFO, "Test completed successfully");
    }
}