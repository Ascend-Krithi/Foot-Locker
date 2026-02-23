package com.fl.automation.tests;

import com.fl.automation.core.ConfigReader;
import com.fl.automation.core.DriverFactory;
import com.fl.automation.pages.HomePage;
import com.fl.automation.pages.StoreLocatorPage;
import com.fl.automation.pages.StoreResultsPage;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
Acceptance Criteria ID: 2440
Test Scenario ID: SCRUM-15408 TS-008 TC-001
Test Case ID: 2440
Description: Search for invalid location and verify error or no results
*/
public class TS008_TC001_2440 {
    private WebDriver driver;

    @BeforeMethod
    public void setUp() {
        DriverFactory.initDriver("chrome");
        driver = DriverFactory.getDriver();
        driver.get(ConfigReader.get("base.url"));
    }

    @Test
    public void testSearchInvalidLocationShowsNoResults() {
        HomePage homePage = new HomePage(driver);
        homePage.clickFindAStore();
        homePage.clickSelectMyStore();
        StoreLocatorPage locatorPage = new StoreLocatorPage(driver);
        locatorPage.enterLocation("InvalidLocation123");
        locatorPage.clickSearchForStores();
        StoreResultsPage resultsPage = new StoreResultsPage(driver);
        Assert.assertTrue(resultsPage.isNoResultsMessageDisplayed(), "No results or error message not displayed");
    }

    @AfterMethod
    public void tearDown() {
        DriverFactory.quitDriver();
    }
}
