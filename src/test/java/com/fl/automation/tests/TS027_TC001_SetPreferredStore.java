package com.fl.automation.tests;

import com.aventstack.extentreports.Status;
import com.fl.automation.core.BaseTest;
import com.fl.automation.helpers.StoreLocatorHelper;
import com.fl.automation.pages.HomePage;
import com.fl.automation.utils.ExtentManager;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class TS027_TC001_SetPreferredStore extends BaseTest {
    
    @Test(description = "TC4173: SCRUM-17166 TS-004 TC-001 - Set preferred store")
    public void setPreferredStore() {
        ExtentManager.getTest().log(Status.INFO, "Starting test: Set preferred store");
        
        HomePage homePage = new HomePage(driver);
        StoreLocatorHelper storeLocator = new StoreLocatorHelper(driver);
        
        ExtentManager.getTest().log(Status.INFO, "Navigating to Foot Locker homepage");
        homePage.navigateToHomePage(BASE_URL);
        ExtentManager.getTest().log(Status.PASS, "Home page loaded");
        
        ExtentManager.getTest().log(Status.INFO, "Opening Store Locator");
        storeLocator.clickFindAStore();
        ExtentManager.getTest().log(Status.PASS, "Store Locator opened");
        
        ExtentManager.getTest().log(Status.INFO, "Searching for stores");
        storeLocator.enterLocation("Boston, MA");
        storeLocator.clickSearchButton();
        ExtentManager.getTest().log(Status.PASS, "Search executed");
        
        ExtentManager.getTest().log(Status.INFO, "Verifying stores are displayed");
        List<WebElement> stores = storeLocator.getStoreResults();
        Assert.assertTrue(stores.size() > 0, "Stores should be displayed");
        ExtentManager.getTest().log(Status.PASS, "Stores displayed");
        
        ExtentManager.getTest().log(Status.INFO, "Setting first store as preferred");
        storeLocator.setPreferredStore(0);
        ExtentManager.getTest().log(Status.PASS, "Preferred store set");
        
        ExtentManager.getTest().log(Status.INFO, "Test completed successfully");
    }
}