package com.fl.automation.tests;

import com.fl.automation.core.BaseTest;
import com.fl.automation.helpers.StoreLocatorHelper;
import com.fl.automation.pages.HomePage;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TS001_TC004_ extends BaseTest {

    @Test(description = "Test Case - SCRUM-17166 TS-001 TC-004: Validate Specific Store Address Appears in Search Results")
    public void testSpecificStoreAddressInResults() {
        HomePage homePage = new HomePage(driver);
        StoreLocatorHelper storeHelper = new StoreLocatorHelper(driver);

        homePage.clickFindAStore();
        homePage.clickSelectMyStore();

        storeHelper.enterLocation("Boston, MA");
        storeHelper.clickSearchForStores();

        Assert.assertTrue(storeHelper.isStoreResultsDisplayed(), 
            "Search results should be displayed");

        WebElement storeCard = storeHelper.findStoreByAddress("375 Washington Street");
        Assert.assertNotNull(storeCard, 
            "Store with address '375 Washington Street, Boston, MA 02108' should be visible in the results");
    }
}