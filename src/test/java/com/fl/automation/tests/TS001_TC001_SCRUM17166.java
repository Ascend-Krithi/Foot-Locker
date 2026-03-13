package com.fl.automation.tests;

import com.fl.automation.core.BaseTest;
import com.fl.automation.pages.HomePage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TS001_TC001_SCRUM17166 extends BaseTest {
    
    @Test(description = "SCRUM-17166 TS-001 TC-001: Verify Find a Store popup appears")
    public void testFindStorePopup() {
        HomePage homePage = new HomePage(driver);
        
        Assert.assertTrue(driver.getTitle().contains("Foot Locker"), "Page title should contain Foot Locker");
        
        homePage.acceptCookies();
        
        homePage.clickFindStore();
        
        Assert.assertTrue(homePage.isSelectMyStoreLinkVisible(), "Select My Store link should be visible");
    }
}