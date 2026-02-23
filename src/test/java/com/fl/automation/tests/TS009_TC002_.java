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
 * Test Scenario ID: SCRUM-15408 TS-009
 * Test Case ID: TC-002
 * Description: Launch homepage, leave location textbox empty, click Search if enabled and verify error message is displayed
 */
public class TS009_TC002_ {
    private WebDriver driver;

    @BeforeMethod
    public void setUp() {
        DriverFactory.initDriver();
        driver = DriverFactory.getDriver();
        driver.get(ConfigReader.get("base.url"));
    }

    @Test
    public void testErrorMessageWhenLocationEmptyAndSearchClicked() {
        HomePage homePage = new HomePage(driver);
        homePage.clickFindAStore();

        StoreLocatorPage locatorPage = new StoreLocatorPage(driver);
        locatorPage.clickSelectMyStore();

        StoreResultsPage resultsPage = new StoreResultsPage(driver);
        
        if (resultsPage.isSearchButtonEnabled()) {
            resultsPage.clickSearchButton();
            Assert.assertTrue(resultsPage.isErrorMessageDisplayed(), "Error message not displayed when location field is empty");
        }
    }

    @AfterMethod
    public void tearDown() {
        DriverFactory.quitDriver();
    }
}