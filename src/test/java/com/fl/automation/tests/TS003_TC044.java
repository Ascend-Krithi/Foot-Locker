package com.fl.automation.tests;

import com.fl.automation.core.BaseTest;
import com.fl.automation.helpers.StoreLocatorHelper;
import com.fl.automation.pages.HomePage;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.util.List;

public class TS003_TC044 extends BaseTest {

    @Test(description = "TC4172: Verify specific store address 375 Washington Street Boston MA in results")
    public void testVerifySpecificStoreAddress() throws InterruptedException {
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

        storeHelper.searchForStore("Boston, MA");
        Thread.sleep(3000);

        List<WebElement> storeResults = storeHelper.findStoreResultCards();
        Assert.assertFalse(storeResults.isEmpty(), "Store search results should be displayed");

        boolean foundTargetStore = false;
        for (WebElement storeCard : storeResults) {
            String address = storeHelper.getStoreAddress(storeCard);
            if (address.contains("375 Washington Street") && address.contains("Boston") && address.contains("02108")) {
                foundTargetStore = true;
                break;
            }
        }
        Assert.assertTrue(foundTargetStore, "Store at 375 Washington Street, Boston, MA 02108 should be in results");
    }
}