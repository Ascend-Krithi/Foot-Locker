package com.fl.automation.tests;

import com.fl.automation.core.BaseTest;
import com.fl.automation.helpers.StoreLocatorHelper;
import com.fl.automation.pages.HomePage;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.util.List;

public class TS001_TC003 extends BaseTest {

    @Test(description = "TC3195: Launch, open store locator, enter Boston MA, search and verify results")
    public void testStoreLocatorSearchBoston() throws InterruptedException {
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
        Assert.assertTrue(storeResults.size() > 0, "At least one store should be found in Boston, MA");
    }
}