package com.fl.automation.tests;

import com.fl.automation.core.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TS001_TC001_LaunchHomepageVerifyFindStore extends BaseTest {
    
    @Test(description="Test Case - SCRUM-17166 TS-001 TC-001: Launch homepage, click Find a Store, verify popup and Select My Store link")
    public void testLaunchHomepageVerifyFindStore() {
        homePage.acceptCookies();
        
        Assert.assertTrue(homePage.isOnFootLockerDomain(), "Homepage should load successfully");
        
        homePage.clickFindStore();
        
        Assert.assertTrue(homePage.isStoreLocatorPopupDisplayed(), "Popup should appear below Find a Store displaying the message");
        
        homePage.clickSelectMyStore();
        
        storeLocatorHelper.waitForStoreLocatorToLoad();
        Assert.assertTrue(storeLocatorHelper.isStoreLocatorModalVisible(), "Select My Store link should be present and visible");
    }
}