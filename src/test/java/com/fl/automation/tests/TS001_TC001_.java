package com.fl.automation.tests;

import com.fl.automation.core.BaseTest;
import com.fl.automation.pages.HomePage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TS001_TC001_ extends BaseTest {

    @Test
    public void testFindStorePopupAndSelectMyStoreLink() {
        HomePage homePage = new HomePage(driver);

        Assert.assertTrue(driver.getTitle().contains("Foot Locker") || driver.getCurrentUrl().contains("footlocker.com"),
            "Step 1: Sneakers, Apparel & More | Foot Locker homepage should load");

        homePage.clickFindStore();

        Assert.assertTrue(homePage.isStorePopupDisplayed(),
            "Step 2: A popup should appear below 'Find a Store' displaying the message 'Choose a preferred store to make shopping easier'");

        Assert.assertTrue(homePage.isSelectMyStoreLinkVisible(),
            "Step 3: 'Select My Store' link should be visible within the popup");
    }
}
