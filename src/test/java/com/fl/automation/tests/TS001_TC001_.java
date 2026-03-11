package com.fl.automation.tests;

import com.fl.automation.core.BaseTest;
import com.fl.automation.pages.HomePage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TS001_TC001_ extends BaseTest {

    @Test(description = "Test Case - SCRUM-17166 TS-001 TC-001")
    public void testFindAStorePopupAndSelectMyStoreLink() {
        driver.get("https://www.footlocker.com");

        HomePage homePage = new HomePage(driver);

        homePage.dismissCookieConsent();

        Assert.assertTrue(driver.getTitle().contains("Foot Locker"), "Homepage did not load correctly");

        homePage.clickFindStore();

        Assert.assertTrue(homePage.isStorePopupDisplayed(), "Store popup did not appear");

        Assert.assertTrue(homePage.isSelectMyStoreLinkVisible(), "Select My Store link is not visible");
    }
}
