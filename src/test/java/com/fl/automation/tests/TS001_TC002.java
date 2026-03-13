package com.fl.automation.tests;

import com.fl.automation.core.BaseTest;
import com.fl.automation.helpers.StoreLocatorHelper;
import com.fl.automation.pages.HomePage;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TS001_TC002 extends BaseTest {

    @Test(description = "TC3194: Launch, click Find a Store, click Select My Store, verify Location textbox and Search button")
    public void testStoreLocatorPopupElements() throws InterruptedException {
        HomePage homePage = new HomePage(driver);
        StoreLocatorHelper storeHelper = new StoreLocatorHelper(driver);

        homePage.navigateToHomePage(BASE_URL);
        Thread.sleep(2000);
        homePage.acceptCookies();
        homePage.closeModalIfPresent();

        WebElement findStoreLink = storeHelper.findStoreLink();
        Assert.assertNotNull(findStoreLink, "Find a Store link should be present");
        storeHelper.clickWithJsFallback(findStoreLink);
        Thread.sleep(2000);

        WebElement selectMyStoreLink = storeHelper.findSelectMyStoreLink();
        Assert.assertNotNull(selectMyStoreLink, "Select My Store link should be present");
        storeHelper.clickWithJsFallback(selectMyStoreLink);
        Thread.sleep(2000);

        WebElement searchInput = storeHelper.findSearchInput();
        Assert.assertNotNull(searchInput, "Location textbox should be present");
        Assert.assertTrue(searchInput.isDisplayed(), "Location textbox should be visible");

        WebElement searchButton = storeHelper.findSearchButton();
        Assert.assertNotNull(searchButton, "Search for Stores button should be present");
        Assert.assertTrue(searchButton.isDisplayed(), "Search for Stores button should be visible");
    }
}