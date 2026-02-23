package com.fl.automation.tests;

import com.fl.automation.core.ConfigReader;
import com.fl.automation.core.DriverFactory;
import com.fl.automation.pages.HomePage;
import com.fl.automation.pages.StoreLocatorPage;
import com.fl.automation.pages.StoreResultsPage;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class TS009_TC002_TestCase_SCRUM15408_TS009_TC002 {
    @BeforeMethod
    public void setUp() {
        DriverFactory.initDriver(ConfigReader.get("browser"));
        DriverFactory.getDriver().get(ConfigReader.get("baseUrl"));
    }

    @Test
    public void testStoreResultsCount() {
        HomePage homePage = new HomePage();
        homePage.clickStoreLocator();
        StoreLocatorPage locatorPage = new StoreLocatorPage();
        locatorPage.enterZipCode("60601");
        locatorPage.clickSearch();
        StoreResultsPage resultsPage = new StoreResultsPage();
        int count = resultsPage.getStoreCount();
        Assert.assertTrue(count >= 0, "Store results count is negative");
    }

    @AfterMethod
    public void tearDown() {
        DriverFactory.quitDriver();
    }
}
