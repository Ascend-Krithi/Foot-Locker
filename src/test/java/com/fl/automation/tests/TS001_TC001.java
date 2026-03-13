package com.fl.automation.tests;

import com.fl.automation.core.BaseTest;
import com.fl.automation.helpers.StoreLocatorHelper;
import com.fl.automation.pages.HomePage;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TS001_TC001 extends BaseTest {

    @Test(description = "TC3193: SCRUM-17166 TS-001 TC-001 - Launch footlocker.com, click Find a Store, verify popup and Select My Store link")
    public void testLaunchAndVerifyStoreLocator() {
        HomePage homePage = new HomePage(driver);
        StoreLocatorHelper storeHelper = new StoreLocatorHelper(driver);

        homePage.navigateToHomePage(BASE_URL);
        homePage.acceptCookies();
        homePage.closeModalIfPresent();

        WebElement findStoreLink = homePage.getFindAStoreLink();
        Assert.assertNotNull(findStoreLink, "Find a Store link should be present");
        
        homePage.clickFindAStore();
        
        WebElement selectMyStoreLink = storeHelper.findSelectMyStoreLink();
        Assert.assertNotNull(selectMyStoreLink, "Select My Store link should be visible after clicking Find a Store");
    }
}