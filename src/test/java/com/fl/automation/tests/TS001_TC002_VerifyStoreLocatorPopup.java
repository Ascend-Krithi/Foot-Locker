package com.fl.automation.tests;

import com.fl.automation.core.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TS001_TC002_VerifyStoreLocatorPopup extends BaseTest {

    @Test(description = "Test Case - SCRUM-17166 TS-001 TC-002: Verify Store Locator popup elements")
    public void testVerifyStoreLocatorPopup() {
        homePage.clickFindStoreButton();
        Assert.assertTrue(homePage.isFindStorePopupDisplayed(), 
            "Popup should appear below Find a Store");
        
        homePage.clickSelectMyStoreLink();
        
        Assert.assertTrue(homePage.isStoreLocatorModalDisplayed(), 
            "Find a Store popup window should open");
        
        Assert.assertTrue(homePage.isLocationTextboxDisplayed(), 
            "Location textbox should be present");
        
        Assert.assertTrue(homePage.isSearchButtonDisplayed(), 
            "Search for Stores button should be present");
    }
}