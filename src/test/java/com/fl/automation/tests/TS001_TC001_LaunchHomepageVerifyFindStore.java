package com.fl.automation.tests;

import com.fl.automation.core.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TS001_TC001_LaunchHomepageVerifyFindStore extends BaseTest {

    @Test(description = "Test Case - SCRUM-17166 TS-001 TC-001: Launch homepage and verify Find a Store functionality")
    public void testLaunchHomepageVerifyFindStore() {
        Assert.assertTrue(homePage.isOnFootLockerDomain(), "Homepage should load successfully");
        
        homePage.clickFindStoreButton();
        
        Assert.assertTrue(homePage.isFindStorePopupDisplayed(), 
            "A popup should appear below Find a Store displaying the message Choose a preferred store to make shopping easier");
        
        Assert.assertTrue(homePage.isSelectMyStoreLinkVisible(), 
            "Select My Store link should be present and visible");
    }
}