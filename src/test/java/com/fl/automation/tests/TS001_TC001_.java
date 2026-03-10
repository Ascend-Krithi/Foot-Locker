package com.fl.automation.tests;

import com.fl.automation.core.BaseTest;
import com.fl.automation.pages.HomePage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TS001_TC001_ extends BaseTest {
    @Test(description = "TC 3193: Launch footlocker.com, click Find a Store, verify Select My Store link appears")
    public void testFindAStoreLinkAppears() {
        test = extent.createTest("TC 3193: Find a Store - Select My Store link appears");
        driver.get("https://www.footlocker.com/");
        HomePage homePage = new HomePage(driver);
        homePage.clickFindAStore();
        boolean linkVisible = false;
        try {
            linkVisible = homePage.getStoreLocatorHelper().selectMyStoreLink().isDisplayed();
        } catch (Exception ignored) {}
        Assert.assertTrue(linkVisible, "Select My Store link should be visible");
    }
}
