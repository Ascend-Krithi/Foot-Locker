package com.fl.automation.tests;

import com.fl.automation.core.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * TC 3194 - Test Case SCRUM-17166 TS-001 TC-002
 * Verify store locator popup elements (Location textbox and Search button)
 */
public class TS001_TC002_SCRUM17166 extends BaseTest {

    @Test(description = "TC 3194: Verify store locator popup elements (Location textbox and Search button)", priority = 2)
    public void testStoreLocatorPopupElements() {
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

            // Step 4: Check for 'Location' textbox and 'Search for Stores' button
            logInfo("Step 4: Checking for 'Location' textbox and 'Search for Stores' button");
            
            boolean isLocationTextboxPresent = storeLocatorHelper.isLocationTextboxPresent();
            boolean isSearchButtonPresent = storeLocatorHelper.isSearchButtonPresent();
            
            // Expected: Both elements present
            Assert.assertTrue(isLocationTextboxPresent, "Location textbox is not present");
            Assert.assertTrue(isSearchButtonPresent, "Search for Stores button is not present");
            logPass("Step 4 Passed: Both 'Location' textbox and 'Search for Stores' button are present");

            logPass("TC 3194 - All steps passed successfully");
            
        } catch (Exception e) {
            logFail("Test failed with exception: " + e.getMessage());
            throw e;
        }
    }
}