package com.fl.automation.tests;

import com.fl.automation.core.BaseTest;
import com.fl.automation.pages.HomePage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TS001_TC001_VerifyFindStorePopup extends BaseTest {

    @Test(description = "TC_3193: SCRUM-17166 TS-001 TC-001 - Verify Find a Store popup and Select My Store link")
    public void verifyFindStorePopupAndSelectMyStore() {
        HomePage homePage = new HomePage(driver);
        
        homePage.navigateToHomePage(baseUrl);
        Assert.assertTrue(homePage.isFindAStoreDisplayed(), "Find a Store link should be displayed");
        
        homePage.clickFindAStore();
        Assert.assertTrue(homePage.isSelectMyStoreDisplayed(), "Select My Store should be displayed after clicking Find a Store");
    }
}