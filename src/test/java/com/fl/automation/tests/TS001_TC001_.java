package com.fl.automation.tests;

import com.fl.automation.core.BaseTest;
import com.fl.automation.pages.HomePage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TS001_TC001_ extends BaseTest {

    @Test(description = "Test Case - SCRUM-17166 TS-001 TC-001: Verify Find Store popup and Select My Store link")
    public void verifyFindStorePopupAndSelectMyStoreLink() {
        HomePage homePage = new HomePage(driver);

        Assert.assertTrue(driver.getTitle().contains("Foot Locker") || driver.getCurrentUrl().contains("footlocker.com"), "Homepage did not load correctly");

        homePage.clickFindStore();

        Assert.assertTrue(homePage.isStorePopupDisplayed(), "Store popup is not displayed after clicking Find a Store");

        Assert.assertTrue(homePage.isSelectMyStoreLinkVisible(), "Select My Store link is not visible within the popup");
    }
}