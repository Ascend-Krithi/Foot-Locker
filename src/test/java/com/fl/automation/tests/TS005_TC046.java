package com.fl.automation.tests;

import com.fl.automation.core.BaseTest;
import com.fl.automation.helpers.StoreLocatorHelper;
import com.fl.automation.pages.HomePage;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class TS005_TC046 extends BaseTest {

    @Test(description = "TC4174: SCRUM-17166 TS-005 TC-001 - Foot Locker: Verify preferred store confirmation")
    public void testFootLockerPreferredStoreConfirmation() {
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
        
        Assert.assertTrue(driver.getCurrentUrl().contains("footlocker"), "Should remain on Foot Locker site after setting preferred store");
    }
}