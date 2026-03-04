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
 * Test Case ID: 2901
 * Test Case Name: Test Case - SCRUM-17166 TS-001 TC-002
 * Description: Launch the Foot Locker homepage, click the 'Find a Store' link/button, click the 'Select My Store' link in the popup, and verify a popup window opens with a 'Location' textbox and a 'Search for Stores' button.
 */
public class TS001_TC002_2901 {

    private WebDriver driver;

    @BeforeMethod
    public void setUp() {
        DriverFactory.initDriver();
        driver = DriverFactory.getDriver();
        driver.get(ConfigReader.get("base.url"));
    }

    @Test
    public void testSelectMyStoreOpensSearchWindow() {
        HomePage homePage = new HomePage(driver);
        homePage.clickFindAStore();

        StoreLocatorPage locatorPage = new StoreLocatorPage(driver);
        locatorPage.clickSelectMyStore();

        StoreResultsPage resultsPage = new StoreResultsPage(driver);
        Assert.assertTrue(resultsPage.isSearchInputDisplayed(), "Location textbox not displayed");
        Assert.assertTrue(resultsPage.isSearchButtonDisplayed(), "Search for Stores button not displayed");
    }

    @AfterMethod
    public void tearDown() {
        DriverFactory.quitDriver();
    }
}