package com.fl.automation.tests;

import com.fl.automation.core.BaseTest;
import com.fl.automation.pages.HomePage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TS001_TC001 extends BaseTest {

    @Test(description = "TC-4200: Verify Find a Store link and Select My Store visibility")
    public void testFindAStoreAndSelectMyStoreVisibility() {
        HomePage homePage = new HomePage(driver);
        
        homePage.navigateToHomePage();
        
        homePage.clickFindAStore();
        
        boolean isSelectMyStoreVisible = homePage.isSelectMyStoreVisible();
        Assert.assertTrue(isSelectMyStoreVisible, "Select My Store link should be visible in the popup");
    }
}