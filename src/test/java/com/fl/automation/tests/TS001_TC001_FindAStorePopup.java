package com.fl.automation.tests;

import com.fl.automation.core.BaseTest;
import com.fl.automation.pages.HomePage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TS001_TC001_FindAStorePopup extends BaseTest {

    @Test(description = "TC_3193: SCRUM-17166 TS-001 TC-001 - Launch footlocker.com, click Find a Store, verify popup with Select My Store link")
    public void testFindAStorePopup() {
        HomePage homePage = new HomePage(driver);
        
        homePage.navigateToFootLocker();
        homePage.clickFindAStore();
        
        boolean isPopupDisplayed = homePage.isStorePopupDisplayed();
        Assert.assertTrue(isPopupDisplayed, "Store locator popup should be displayed");
    }
}