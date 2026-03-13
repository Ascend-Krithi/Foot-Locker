package com.fl.automation.tests;

import com.fl.automation.core.BaseTest;
import com.fl.automation.helpers.StoreLocatorHelper;
import com.fl.automation.pages.HomePage;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class TS001_TC003 extends BaseTest {

    @Test(description = "TC3195: SCRUM-17166 TS-001 TC-003 - Launch, open store locator, enter Boston MA, search and verify results")
    public void testSearchStoresByLocation() {
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
        Assert.assertTrue(storeResults.size() > 0, "Store search results should be displayed for Boston MA");
    }
}