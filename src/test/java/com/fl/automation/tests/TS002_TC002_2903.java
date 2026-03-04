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
 * Test Case ID: 2903
 * Test Case Name: Test Case - SCRUM-17166 TS-002 TC-002
 * Description: Launch the Foot Locker homepage, click the 'Find a Store' link/button, click the 'Select My Store' link in the popup, enter 'InvalidLocation123' in the 'Location' textbox, click the 'Search for Stores' button, and verify a message indicating 'no results found' is displayed.
 */
public class TS002_TC002_2903 {

    private WebDriver driver;

    @BeforeMethod
    public void setUp() {
        DriverFactory.initDriver();
        driver = DriverFactory.getDriver();
        driver.get(ConfigReader.get("base.url"));
    }

    @Test
    public void testSearchForStoresWithInvalidLocation() {
        HomePage homePage = new HomePage(driver);
        homePage.clickFindAStore();

        StoreLocatorPage locatorPage = new StoreLocatorPage(driver);
        locatorPage.clickSelectMyStore();

        StoreResultsPage resultsPage = new StoreResultsPage(driver);
        resultsPage.enterLocation("InvalidLocation123");
        resultsPage.clickSearchButton();

        Assert.assertTrue(resultsPage.isEmptyResultsMessageDisplayed(), "No results found message not displayed");
    }

    @AfterMethod
    public void tearDown() {
        DriverFactory.quitDriver();
    }
}