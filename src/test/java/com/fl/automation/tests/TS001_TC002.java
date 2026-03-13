package com.fl.automation.tests;

import com.fl.automation.core.BaseTest;
import com.fl.automation.helpers.StoreLocatorHelper;
import com.fl.automation.pages.HomePage;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TS001_TC002 extends BaseTest {

    @Test(description = "TC3194: SCRUM-17166 TS-001 TC-002 - Launch, click Find a Store, click Select My Store, verify Location textbox and Search button")
    public void testVerifySearchElements() {
        HomePage homePage = new HomePage(driver);
        StoreLocatorHelper storeHelper = new StoreLocatorHelper(driver);

        homePage.navigateToHomePage(BASE_URL);
        homePage.acceptCookies();
        homePage.closeModalIfPresent();
        homePage.clickFindAStore();

        WebElement selectMyStoreLink = storeHelper.findSelectMyStoreLink();
        Assert.assertNotNull(selectMyStoreLink, "Select My Store link should be present");
        storeHelper.clickWithJsFallback(selectMyStoreLink);

        WebElement searchInput = storeHelper.findSearchInput();
        Assert.assertNotNull(searchInput, "Location search textbox should be visible");
        
        WebElement searchButton = storeHelper.findSearchButton();
        Assert.assertNotNull(searchButton, "Search button should be visible");
    }
}