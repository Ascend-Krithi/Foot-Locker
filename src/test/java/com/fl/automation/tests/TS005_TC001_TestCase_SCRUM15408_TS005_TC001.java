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

public class TS005_TC001_TestCase_SCRUM15408_TS005_TC001 {
    @BeforeMethod
    public void setUp() {
        DriverFactory.initDriver(ConfigReader.get("browser"));
        DriverFactory.getDriver().get(ConfigReader.get("baseUrl"));
    }

    @Test
    public void testNoStoreFound() {
        HomePage homePage = new HomePage();
        homePage.clickStoreLocator();
        StoreLocatorPage locatorPage = new StoreLocatorPage();
        locatorPage.enterZipCode("00000");
        locatorPage.clickSearch();
        StoreResultsPage resultsPage = new StoreResultsPage();
        Assert.assertEquals(resultsPage.getStoreCount(), 0, "Stores found for invalid zip!");
    }

    @AfterMethod
    public void tearDown() {
        DriverFactory.quitDriver();
    }
}
