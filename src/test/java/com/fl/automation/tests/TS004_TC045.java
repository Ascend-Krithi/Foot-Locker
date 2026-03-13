package com.fl.automation.tests;

import com.fl.automation.core.BaseTest;
import com.fl.automation.helpers.StoreLocatorHelper;
import com.fl.automation.pages.HomePage;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class TS004_TC045 extends BaseTest {

    @Test(description = "TC4173: SCRUM-17166 TS-004 TC-001 - Foot Locker: Set preferred store")
    public void testFootLockerSetPreferredStore() {
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
        Assert.assertTrue(storeResults.size() > 0, "Store results should be available");
        
        WebElement setMyStoreButton = storeHelper.findSetMyStoreButton(storeResults.get(0));
        Assert.assertNotNull(setMyStoreButton, "Set My Store button should be present on store card");
    }
}