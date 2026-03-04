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
 * Test Case ID: 2934
 * Test Case Name: Test Case - SCRUM-17166 TS-001 TC-002
 * Description: Ensure Store Locator popup is open, click on 'Select My Store' within the popup, enter 'Boston, MA' in the Location textbox, click 'Search for Stores' button, and verify store results for Boston, MA are displayed in the popup window.
 */
public class TS001_TC002_2934 {

    private WebDriver driver;

    @BeforeMethod
    public void setUp() {
        DriverFactory.initDriver();
        driver = DriverFactory.getDriver();
        driver.get(ConfigReader.get("base.url"));
    }

    @Test
    public void testSearchStoresFromPopup() {
        HomePage homePage = new HomePage(driver);
        homePage.clickFindAStore();

        StoreLocatorPage locatorPage = new StoreLocatorPage(driver);
        locatorPage.clickSelectMyStore();

        StoreResultsPage resultsPage = new StoreResultsPage(driver);
        resultsPage.enterLocation("Boston, MA");
        resultsPage.clickSearchButton();

        Assert.assertTrue(resultsPage.areStoreResultsDisplayed(), "Store results not displayed for Boston, MA");
    }

    @AfterMethod
    public void tearDown() {
        DriverFactory.quitDriver();
    }
}