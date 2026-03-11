package com.fl.automation.tests;

import com.fl.automation.core.BaseTest;
import com.fl.automation.pages.HomePage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TS001_TC001_ extends BaseTest {

    @Test
    public void testFindStorePopupAndSelectMyStoreLink() {
        HomePage homePage = new HomePage(driver);

        homePage.navigateTo("https://www.footlocker.com");
        Assert.assertTrue(driver.getTitle().contains("Foot Locker"), "Homepage did not load correctly");

        homePage.clickFindStore();

        Assert.assertTrue(homePage.isPopupMessageDisplayed(), "Popup message 'Choose a preferred store to make shopping easier' is not displayed");

        Assert.assertTrue(homePage.isSelectMyStoreLinkVisible(), "'Select My Store' link is not visible within the popup");
    }
}