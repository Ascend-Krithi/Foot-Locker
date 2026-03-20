package com.fl.automation.tests;

import com.fl.automation.core.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TS001_TC002_VerifyStoreLocatorPopupElements extends BaseTest {
    
    @Test(description="Test Case - SCRUM-17166 TS-001 TC-002: Launch homepage, click Find a Store, click Select My Store, verify Location textbox and Search for Stores button")
    public void testVerifyStoreLocatorPopupElements() {
        homePage.acceptCookies();
        
        homePage.clickFindStore();
        
        Assert.assertTrue(homePage.isStoreLocatorPopupDisplayed(), "Popup should appear below Find a Store");
        
        homePage.clickSelectMyStore();
        
        storeLocatorHelper.waitForStoreLocatorToLoad();
        
        Assert.assertTrue(storeLocatorHelper.isStoreLocatorModalVisible(), "Find a Store popup window should open with Location textbox and Search for Stores button");
    }
}