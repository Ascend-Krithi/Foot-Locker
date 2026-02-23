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
 * Acceptance Criteria ID: 2435
 * Test Scenario ID: SCRUM-15408 TS-003
 * Test Case ID: TC-002
 * Description: Search for an invalid city and verify empty results message is displayed
 */
public class TS003_TC002_2435 {
    private WebDriver driver;

    @BeforeMethod
    public void setUp() {
        DriverFactory.initDriver();
        driver = DriverFactory.getDriver();
        driver.get(ConfigReader.get("base.url"));
    }

    @Test
    public void testSearchInvalidCity() {
        HomePage homePage = new HomePage(driver);
        homePage.clickFindAStore();
        StoreLocatorPage locatorPage = new StoreLocatorPage(driver);
        locatorPage.clickSelectMyStore();
        StoreResultsPage resultsPage = new StoreResultsPage(driver);
        resultsPage.searchForStore("InvalidCityXYZ");
        Assert.assertTrue(resultsPage.isEmptyResultsMessageDisplayed(), "Empty results message not displayed for invalid city");
    }

    @AfterMethod
    public void tearDown() {
        DriverFactory.quitDriver();
    }
}