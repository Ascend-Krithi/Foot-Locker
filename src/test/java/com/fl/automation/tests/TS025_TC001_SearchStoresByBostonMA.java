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

public class TS025_TC001_SearchStoresByBostonMA extends BaseTest {
    
    @Test(description = "TC4171: SCRUM-17166 TS-002 TC-001 - Search stores by Boston MA")
    public void searchStoresByBostonMA() {
        ExtentManager.getTest().log(Status.INFO, "Starting test: Search stores by Boston MA");
        
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
        
        ExtentManager.getTest().log(Status.INFO, "Verifying search results");
        List<WebElement> results = storeLocator.getStoreResults();
        Assert.assertTrue(results.size() > 0, "Store results should be displayed for Boston, MA");
        ExtentManager.getTest().log(Status.PASS, "Found " + results.size() + " stores in Boston, MA");
        
        ExtentManager.getTest().log(Status.INFO, "Test completed successfully");
    }
}