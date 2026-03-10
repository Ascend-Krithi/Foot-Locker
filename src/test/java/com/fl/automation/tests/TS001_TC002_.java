package com.fl.automation.tests;

import com.fl.automation.core.BaseTest;
import com.fl.automation.helpers.StoreLocatorHelper;
import com.fl.automation.pages.HomePage;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TS001_TC002_ extends BaseTest {

    @Test(description = "Test Case - SCRUM-17166 TS-001 TC-002: Validate Store Locator Popup Contains Location Textbox and Search Button")
    public void testStoreLocatorPopupElements() {
        HomePage homePage = new HomePage(driver);
        StoreLocatorHelper storeHelper = new StoreLocatorHelper(driver);

        homePage.clickFindAStore();
        Assert.assertTrue(homePage.isFindAStorePopupDisplayed(), 
            "Popup should appear below 'Find a Store'");

        homePage.clickSelectMyStore();

        WebElement locationTextbox = storeHelper.getLocationTextbox();
        Assert.assertNotNull(locationTextbox, 
            "Store locator popup should contain 'Location' textbox");
        Assert.assertTrue(locationTextbox.isDisplayed(), 
            "'Location' textbox should be visible");
    }
}