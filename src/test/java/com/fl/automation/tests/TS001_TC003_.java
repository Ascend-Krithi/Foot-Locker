package com.fl.automation.tests;

import com.fl.automation.core.BaseTest;
import com.fl.automation.pages.HomePage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TS001_TC003_ extends BaseTest {

    @Test
    public void testScenario() {
        HomePage homePage = new HomePage(driver);

        Assert.assertTrue(driver.getTitle().contains("Foot Locker"), "Homepage did not load correctly");

        homePage.clickFindStore();

        Assert.assertTrue(homePage.isSelectMyStoreVisible(), "Popup did not appear");

        homePage.clickSelectMyStore();

        Assert.assertTrue(homePage.isSearchInputVisible(), "Store locator popup did not open");

        homePage.enterLocation("Boston, MA");

        homePage.clickSearchButton();

        Assert.assertTrue(homePage.areStoreResultsDisplayed(), "Search results are not displayed");
    }
}