package com.fl.automation.tests;

import com.fl.automation.core.DriverFactory;
import com.fl.automation.pages.HomePage;
import com.fl.automation.pages.StoreLocatorPage;
import com.fl.automation.pages.StoreResultsPage;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class TS001_TC009_StoreResultsFilteringTest {
    @BeforeMethod
    public void setUp() {
        DriverFactory.initDriver();
    }

    @Test
    public void testStoreResultsFiltering() {
        HomePage homePage = new HomePage();
        homePage.open();
        homePage.clickStoreLocator();
        StoreLocatorPage locatorPage = new StoreLocatorPage();
        locatorPage.searchByZip("10001");
        StoreResultsPage resultsPage = new StoreResultsPage();
        resultsPage.filterResults();
        Assert.assertTrue(resultsPage.getResults().size() > 0);
    }

    @AfterMethod
    public void tearDown() {
        DriverFactory.quitDriver();
    }
}
