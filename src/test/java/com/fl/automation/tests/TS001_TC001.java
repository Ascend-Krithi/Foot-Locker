package com.fl.automation.tests;

import com.fl.automation.core.BaseTest;
import com.fl.automation.pages.HomePage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TS001_TC001 extends BaseTest {

    @Test(description = "TC4200 - TS-001 TC-001: Launch homepage, click Find a Store, verify popup message and Select My Store link")
    public void testLaunchHomepageAndVerifyFindStore() {
        HomePage homePage = new HomePage(driver);

        // Click Find a Store
        homePage.clickFindStore();

        // Verify popup message is displayed
        boolean isPopupDisplayed = homePage.isStorePopupMessageDisplayed();
        Assert.assertTrue(isPopupDisplayed, "Store popup message should be displayed");

        // Verify Select My Store link is visible
        boolean isSelectMyStoreVisible = homePage.isSelectMyStoreLinkVisible();
        Assert.assertTrue(isSelectMyStoreVisible, "Select My Store link should be visible");
    }
}