package com.fl.automation.tests;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.openqa.selenium.WebDriver;
import com.fl.automation.core.DriverFactory;
import com.fl.automation.pages.HomePage;
import com.fl.automation.pages.StoreLocatorPage;
import com.fl.automation.pages.StoreResultsPage;

/**
 * Acceptance Criteria ID: SCRUM-17166
 * Test Scenario ID: TS-001
 * Test Case ID: TC-003
 * Description: Launch app, Click Find a Store, Click Select My Store, Enter Boston MA in Location, Click Search for Stores, Verify search results displayed
 */
public class TS001_TC003_ {
    private WebDriver driver;
    private HomePage homePage;
    private StoreLocatorPage locatorPage;
    private StoreResultsPage resultsPage;

    @BeforeMethod
    public void setUp() {
        driver = DriverFactory.getDriver();
        homePage = new HomePage(driver);
        locatorPage = new StoreLocatorPage(driver);
        resultsPage = new StoreResultsPage(driver);
    }

    @Test
    public void testSearchResultsDisplayed() {
        homePage.launch();
        homePage.clickFindStore();
        locatorPage.clickSelectMyStore();
        locatorPage.enterLocation("Boston MA");
        locatorPage.clickSearchButton();
        Assert.assertTrue(resultsPage.areResultsDisplayed(), "Search results should be displayed");
    }

    @AfterMethod
    public void tearDown() {
        DriverFactory.quitDriver();
    }
}
