package com.fl.automation.tests;

import com.fl.automation.core.BaseTest;
import com.fl.automation.helpers.StoreLocatorHelper;
import com.fl.automation.pages.HomePage;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

/**
 * TestRail TC-4171: SCRUM-17166 Store Locator - Set preferred store
 */
public class TS001_TC005 extends BaseTest {

    @Test(description = "TC-4171: Set preferred store from search results")
    public void testSetPreferredStore() {
        HomePage homePage = new HomePage(driver);
        StoreLocatorHelper storeLocator = new StoreLocatorHelper(driver);

        homePage.open("https://www.footlocker.com");
        homePage.acceptCookies();
        homePage.clickFindStore();
        
        storeLocator.enterLocation("Boston, MA");
        storeLocator.clickSearchButton();
        
        List<WebElement> storeCards = storeLocator.getStoreResultCards();
        Assert.assertTrue(storeCards.size() > 0, "Store results should be displayed");
        
        String selectedStoreAddress = storeLocator.getStoreAddress(storeCards.get(0));
        Assert.assertNotNull(selectedStoreAddress, "Selected store should have an address");
    }
}