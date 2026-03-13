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

public class TS001_TC003_SearchStoresByLocation extends BaseTest {
    
    @Test(description = "TC3195: SCRUM-17166 TS-001 TC-003 - Search stores by location Boston, MA")
    public void searchStoresByLocation() {
        ExtentManager.getTest().log(Status.INFO, "Starting test: Search stores by location Boston, MA");
        
        HomePage homePage = new HomePage(driver);
        StoreLocatorHelper storeLocator = new StoreLocatorHelper(driver);
        
        ExtentManager.getTest().log(Status.INFO, "Navigating to Foot Locker homepage");
        homePage.navigateToHomePage(BASE_URL);
        ExtentManager.getTest().log(Status.PASS, "Home page loaded");
        
        ExtentManager.getTest().log(Status.INFO, "Opening Store Locator");
        storeLocator.clickFindAStore();
        ExtentManager.getTest().log(Status.PASS, "Store Locator opened");
        
        ExtentManager.getTest().log(Status.INFO, "Entering location: Boston, MA");
        storeLocator.enterLocation("Boston, MA");
        ExtentManager.getTest().log(Status.PASS, "Location entered");
        
        ExtentManager.getTest().log(Status.INFO, "Clicking Search button");
        storeLocator.clickSearchButton();
        ExtentManager.getTest().log(Status.PASS, "Search button clicked");
        
        ExtentManager.getTest().log(Status.INFO, "Verifying store results are displayed");
        List<WebElement> storeResults = storeLocator.getStoreResults();
        Assert.assertTrue(storeResults.size() > 0, "Store results should be displayed");
        ExtentManager.getTest().log(Status.PASS, "Found " + storeResults.size() + " stores");
        
        ExtentManager.getTest().log(Status.INFO, "Test completed successfully");
    }
}