package com.fl.automation.tests;

import com.fl.automation.core.BaseTest;
import com.fl.automation.helpers.StoreLocatorHelper;
import com.fl.automation.pages.HomePage;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class TS003_TC044 extends BaseTest {

    @Test(description = "TC4172: SCRUM-17166 TS-003 TC-001 - Foot Locker: Verify specific store address in results")
    public void testFootLockerVerifyStoreAddress() {
        HomePage homePage = new HomePage(driver);
        StoreLocatorHelper storeHelper = new StoreLocatorHelper(driver);

        homePage.navigateToHomePage(BASE_URL);
        homePage.acceptCookies();
        homePage.closeModalIfPresent();
        homePage.clickFindAStore();

        WebElement selectMyStoreLink = storeHelper.findSelectMyStoreLink();
        if (selectMyStoreLink != null) {
            storeHelper.clickWithJsFallback(selectMyStoreLink);
        }

        storeHelper.searchForStore("Boston MA");

        List<WebElement> storeResults = storeHelper.findStoreResultCards();
        Assert.assertTrue(storeResults.size() > 0, "Store results should be present");
        
        String firstStoreAddress = storeHelper.getStoreAddress(storeResults.get(0));
        Assert.assertFalse(firstStoreAddress.isEmpty(), "Store address should be displayed in results");
    }
}