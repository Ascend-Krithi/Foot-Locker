package com.fl.automation.tests;

import com.fl.automation.core.BaseTest;
import com.fl.automation.helpers.StoreLocatorHelper;
import com.fl.automation.pages.HomePage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TS001_TC007_VerifyStorePersistence extends BaseTest {

    @Test(description = "TC4206 - Set 375 Washington Street as preferred, navigate to sneakers page, verify store remains set")
    public void testVerifyStorePersistence() throws InterruptedException {
        HomePage homePage = new HomePage(driver);
        StoreLocatorHelper storeLocatorHelper = new StoreLocatorHelper(driver);

        homePage.acceptCookiesIfPresent();
        Thread.sleep(2000);

        homePage.clickFindStore();
        Thread.sleep(2000);

        homePage.clickSelectMyStore();
        Thread.sleep(3000);

        storeLocatorHelper.enterLocation("Boston, MA");
        Thread.sleep(1000);

        storeLocatorHelper.clickSearchForStores();
        Thread.sleep(4000);

        storeLocatorHelper.clickSetMyStoreForAddress("375 Washington Street");
        Thread.sleep(3000);

        driver.navigate().to("https://www.footlocker.com/category/mens/shoes/sneakers.html");
        Thread.sleep(3000);

        Assert.assertTrue(driver.getCurrentUrl().contains("sneakers"), "Page did not load successfully");

        boolean storeStillSet = homePage.isSelectedStoreDisplayedInHeader("375 Washington Street");
        Assert.assertTrue(storeStillSet, "Selected store (375 Washington Street, Boston, MA 02108) is not still shown as preferred after navigation");
    }
}