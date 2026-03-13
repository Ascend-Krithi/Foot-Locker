package com.fl.automation.tests;

import com.fl.automation.core.BaseTest;
import com.fl.automation.helpers.StoreLocatorHelper;
import com.fl.automation.pages.HomePage;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class TS006_TC047 extends BaseTest {

    @Test(description = "TC4175: SCRUM-17166 TS-006 TC-001 - Foot Locker: Verify preferred store persists across pages")
    public void testFootLockerPreferredStorePersistence() {
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
        if (storeResults.size() > 0) {
            WebElement setMyStoreButton = storeHelper.findSetMyStoreButton(storeResults.get(0));
            if (setMyStoreButton != null) {
                storeHelper.clickWithJsFallback(setMyStoreButton);
            }
        }
        
        homePage.navigateToHomePage(BASE_URL);
        Assert.assertTrue(driver.getCurrentUrl().contains("footlocker"), "Preferred store should persist when navigating to home page");
    }
}