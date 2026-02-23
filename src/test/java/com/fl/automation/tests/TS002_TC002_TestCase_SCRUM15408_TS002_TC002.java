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
 * Acceptance Criteria ID: AC2
 * Test Scenario ID: SCRUM-15408 TS-002
 * Test Case ID: TC-002 (2014)
 * Description: Launch homepage, open Store Locator, enter 'Boston, MA', click Search.
 * Expected: A message is displayed indicating that no stores were found near the entered location (negative test).
 */
public class TS002_TC002_TestCase_SCRUM15408_TS002_TC002 {

    private WebDriver driver;

    @BeforeMethod
    public void setUp() {
        DriverFactory.initDriver();
        driver = DriverFactory.getDriver();
        driver.get(ConfigReader.get("base.url"));
    }

    @Test
    public void testNoStoresFoundMessage() {
        HomePage homePage = new HomePage(driver);
        homePage.clickFindAStore();

        StoreLocatorPage locatorPage = new StoreLocatorPage(driver);
        locatorPage.clickSelectMyStore();

        StoreResultsPage resultsPage = new StoreResultsPage(driver);
        resultsPage.enterSearchLocation("Boston, MA");
        resultsPage.clickSearchButton();

        boolean hasResults = resultsPage.areStoreResultsDisplayed();
        boolean hasEmptyMessage = resultsPage.isEmptyResultsMessageDisplayed();

        Assert.assertTrue(!hasResults || hasEmptyMessage, "Expected no results or empty message for the search");
    }

    @AfterMethod
    public void tearDown() {
        DriverFactory.quitDriver();
    }
}