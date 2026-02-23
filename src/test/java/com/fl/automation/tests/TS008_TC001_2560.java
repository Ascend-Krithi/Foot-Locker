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
 * Test Scenario ID: SCRUM-15408 TS-008
 * Test Case ID: TC-001 (2560)
 * Description: Enter InvalidLocation123 and verify error message or no results
 */
public class TS008_TC001_2560 {
    private WebDriver driver;

    @BeforeMethod
    public void setUp() {
        DriverFactory.initDriver();
        driver = DriverFactory.getDriver();
        driver.get(ConfigReader.get("base.url"));
    }

    @Test
    public void testInvalidLocationHandling() {
        HomePage homePage = new HomePage(driver);
        homePage.clickFindAStore();
        
        StoreLocatorPage locatorPage = new StoreLocatorPage(driver);
        locatorPage.clickSelectMyStore();
        
        StoreResultsPage resultsPage = new StoreResultsPage(driver);
        resultsPage.enterLocation("InvalidLocation123");
        resultsPage.clickSearchButton();
        
        Assert.assertTrue(resultsPage.isEmptyResultsMessageDisplayed() || !resultsPage.areStoreResultsDisplayed(), 
                         "Appropriate error message is displayed or no store results are shown");
    }

    @AfterMethod
    public void tearDown() {
        DriverFactory.quitDriver();
    }
}