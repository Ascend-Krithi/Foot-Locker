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
 * Acceptance Criteria ID: 1650
 * Test Scenario ID: SCRUM-15408 TS-006
 * Test Case ID: TC-001
 * Description: Verify multiple stores are returned for a city
 */
public class TS006_TC001_ {
    private WebDriver driver;

    @BeforeMethod
    public void setUp() {
        DriverFactory.initDriver();
        driver = DriverFactory.getDriver();
        driver.get(ConfigReader.get("base.url"));
    }

    @Test
    public void testMultipleStoresReturned() {
        HomePage homePage = new HomePage(driver);
        homePage.clickFindAStore();
        StoreLocatorPage locatorPage = new StoreLocatorPage(driver);
        locatorPage.clickSelectMyStore();
        StoreResultsPage resultsPage = new StoreResultsPage(driver);
        resultsPage.enterSearch("Los Angeles");
        resultsPage.clickSearchButton();
        Assert.assertTrue(resultsPage.getStoreResultsCount() > 1, "Less than 2 stores found");
    }

    @AfterMethod
    public void tearDown() {
        DriverFactory.quitDriver();
    }
}
