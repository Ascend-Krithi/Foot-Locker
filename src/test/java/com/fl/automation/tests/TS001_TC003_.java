package com.fl.automation.tests;

import com.fl.automation.core.BaseTest;
import com.fl.automation.pages.HomePage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TS001_TC003_ extends BaseTest {

    @Test
    public void testCase_SCRUM17166_TS001_TC003() {
        HomePage homePage = new HomePage(driver);

        Assert.assertTrue(driver.getTitle().contains("Foot Locker"), "Homepage did not load correctly");

        homePage.clickFindStore();

        homePage.clickSelectMyStore();

        homePage.enterLocation("Boston, MA");

        homePage.clickSearchForStores();

        Assert.assertTrue(homePage.areStoreResultsDisplayed(), "Store search results are not displayed");
        Assert.assertTrue(homePage.storeResultsContainLocation("Boston"), "Search results do not contain stores in or near Boston");
    }
}