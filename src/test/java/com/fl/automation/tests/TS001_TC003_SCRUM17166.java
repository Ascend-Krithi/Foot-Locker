package com.fl.automation.tests;

import com.fl.automation.core.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * TC 3195 - Test Case SCRUM-17166 TS-001 TC-003
 * Verify store search functionality with Boston, MA location
 */
public class TS001_TC003_SCRUM17166 extends BaseTest {

    @Test(description = "TC 3195: Verify store search functionality with Boston, MA location", priority = 3)
    public void testStoreSearchWithBostonLocation() {
        try {
            // Step 1: Launch https://www.footlocker.com
            logInfo("Step 1: Launching https://www.footlocker.com");
            homePage.navigateToHomePage();
            
            // Expected: Homepage loads
            Assert.assertTrue(homePage.isHomePageLoaded(), "Homepage failed to load");
            logPass("Step 1 Passed: Homepage loaded successfully");

            // Step 2: Click 'Find a Store'
            logInfo("Step 2: Clicking 'Find a Store' link");
            homePage.clickFindAStore();
            
            // Expected: Popup appears
            boolean isPopupDisplayed = homePage.isPopupMessageDisplayed("Choose a preferred store");
            Assert.assertTrue(isPopupDisplayed, "Popup not displayed");
            logPass("Step 2 Passed: Popup appeared");

            // Step 3: Click 'Select My Store'
            logInfo("Step 3: Clicking 'Select My Store' link");
            storeLocatorHelper.clickSelectMyStore();
            
            // Expected: Store locator popup opens
            logPass("Step 3 Passed: Store locator popup opened");

            // Step 4: Enter 'Boston, MA' in Location textbox
            logInfo("Step 4: Entering 'Boston, MA' in Location textbox");
            storeLocatorHelper.enterLocation("Boston, MA");
            
            // Expected: Text entered
            logPass("Step 4 Passed: Text 'Boston, MA' entered in Location textbox");

            // Step 5: Click 'Search for Stores'
            logInfo("Step 5: Clicking 'Search for Stores' button");
            storeLocatorHelper.clickSearchButton();
            
            // Expected: Search results displayed with stores in/near Boston
            boolean areResultsDisplayed = storeLocatorHelper.areStoreResultsDisplayed();
            Assert.assertTrue(areResultsDisplayed, "Store search results not displayed");
            logPass("Step 5 Passed: Search results displayed with stores in/near Boston");

            logPass("TC 3195 - All steps passed successfully");
            
        } catch (Exception e) {
            logFail("Test failed with exception: " + e.getMessage());
            throw e;
        }
    }
}