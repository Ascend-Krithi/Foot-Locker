package com.fl.automation.tests;

import com.fl.automation.core.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * TC 3193 - Test Case SCRUM-17166 TS-001 TC-001
 * Verify Find a Store link and popup with Select My Store link
 */
public class TS001_TC001_SCRUM17166 extends BaseTest {

    @Test(description = "TC 3193: Verify Find a Store link and popup with Select My Store link", priority = 1)
    public void testFindAStoreAndSelectMyStoreLink() {
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
            
            // Expected: Popup appears with message 'Choose a preferred store to make shopping easier'
            boolean isPopupDisplayed = homePage.isPopupMessageDisplayed("Choose a preferred store");
            Assert.assertTrue(isPopupDisplayed, "Popup with expected message not displayed");
            logPass("Step 2 Passed: Popup appeared with message 'Choose a preferred store to make shopping easier'");

            // Step 3: Check for 'Select My Store' link
            logInfo("Step 3: Checking for 'Select My Store' link visibility");
            boolean isSelectMyStoreVisible = storeLocatorHelper.isSelectMyStoreLinkVisible();
            
            // Expected: Link is visible
            Assert.assertTrue(isSelectMyStoreVisible, "'Select My Store' link is not visible");
            logPass("Step 3 Passed: 'Select My Store' link is visible");

            logPass("TC 3193 - All steps passed successfully");
            
        } catch (Exception e) {
            logFail("Test failed with exception: " + e.getMessage());
            throw e;
        }
    }
}