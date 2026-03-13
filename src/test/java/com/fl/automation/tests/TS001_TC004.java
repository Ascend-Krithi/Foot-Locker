package com.fl.automation.tests;

import com.fl.automation.core.BaseTest;
import com.fl.automation.helpers.StoreLocatorHelper;
import com.fl.automation.pages.HomePage;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

/**
 * TestRail TC-4170: SCRUM-17166 Store Locator - Verify store address
 */
public class TS001_TC004 extends BaseTest {

    @Test(description = "TC-4170: Verify store address in search results")
    public void testVerifyStoreAddress() {
        HomePage homePage = new HomePage(driver);
        StoreLocatorHelper storeLocator = new StoreLocatorHelper(driver);

        homePage.open("https://www.footlocker.com");
        homePage.acceptCookies();
        homePage.clickFindStore();
        
        storeLocator.enterLocation("Boston, MA");
        storeLocator.clickSearchButton();
        
        List<WebElement> storeCards = storeLocator.getStoreResultCards();
        Assert.assertTrue(storeCards.size() > 0, "Store results should be displayed");
        
        for (int i = 0; i < Math.min(3, storeCards.size()); i++) {
            String address = storeLocator.getStoreAddress(storeCards.get(i));
            Assert.assertFalse(address.isEmpty(), "Store address should not be empty");
            Assert.assertTrue(address.length() > 10, "Store address should contain meaningful data");
        }
    }
}