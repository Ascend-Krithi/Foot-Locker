package com.fl.automation.tests;

import com.fl.automation.core.BaseTest;
import com.fl.automation.helpers.StoreLocatorHelper;
import com.fl.automation.pages.HomePage;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

/**
 * TestRail TC-3195: SCRUM-17166 TS-001 TC-003
 * Test: Store search Boston MA
 */
public class TS001_TC003 extends BaseTest {

    @Test(description = "TC-3195: Store search Boston MA")
    public void testStoreSearchBostonMA() {
        HomePage homePage = new HomePage(driver);
        StoreLocatorHelper storeLocator = new StoreLocatorHelper(driver);

        homePage.open("https://www.footlocker.com");
        homePage.acceptCookies();
        homePage.clickFindStore();
        
        storeLocator.enterLocation("Boston, MA");
        storeLocator.clickSearchButton();
        
        List<WebElement> storeCards = storeLocator.getStoreResultCards();
        Assert.assertTrue(storeCards.size() > 0, "Store results should be displayed for Boston, MA");
        
        String firstStoreAddress = storeLocator.getStoreAddress(storeCards.get(0));
        Assert.assertTrue(firstStoreAddress.contains("MA") || firstStoreAddress.contains("Massachusetts"), 
            "First store result should contain MA or Massachusetts");
    }
}