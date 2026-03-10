package com.fl.automation.tests;

import com.fl.automation.core.BaseTest;
import com.fl.automation.pages.HomePage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TS001_TC002_ extends BaseTest {

    @Test
    public void testScenario() {
        HomePage homePage = new HomePage(driver);

        Assert.assertTrue(driver.getTitle().contains("Foot Locker"), "Homepage did not load correctly");

        homePage.clickFindStore();

        Assert.assertTrue(homePage.isSelectMyStoreVisible(), "Popup did not appear");

        homePage.clickSelectMyStore();

        Assert.assertTrue(homePage.isSearchInputVisible(), "'Location' textbox is not visible");
        Assert.assertTrue(homePage.isSearchButtonVisible(), "'Search for Stores' button is not visible");
    }
}