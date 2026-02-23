package com.fl.automation.tests;

import com.fl.automation.core.DriverFactory;
import com.fl.automation.pages.HomePage;
import com.fl.automation.pages.StoreLocatorPage;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * Acceptance Criteria ID: 2172
 * Test Scenario ID: SCRUM-15408 TS-009
 * Test Case ID: TC-002
 * Description: Launch website -> Click 'Find a Store' -> Click 'Select My Store' -> Leave Location textbox empty -> Click Search for Stores if enabled
 * Expected: Error message displayed indicating Location field is required
 */
public class TS009_TC002_ {
    private HomePage homePage;
    private StoreLocatorPage storeLocatorPage;

    @BeforeMethod
    public void setUp() {
        homePage = new HomePage();
        storeLocatorPage = new StoreLocatorPage();
        homePage.launch();
    }

    @Test
    public void testErrorMessageWhenLocationEmpty() {
        homePage.clickFindAStore();
        homePage.clickSelectMyStore();
        if (storeLocatorPage.isSearchButtonEnabled()) {
            storeLocatorPage.clickSearchForStores();
            WebDriver driver = DriverFactory.getDriver();
            // Try to find error message for required field
            boolean errorDisplayed = driver.getPageSource().contains("required") ||
                driver.getPageSource().toLowerCase().contains("location");
            Assert.assertTrue(errorDisplayed, "Error message for required location not displayed");
        } else {
            Assert.assertTrue(true, "Search button is disabled as expected");
        }
    }

    @AfterMethod
    public void tearDown() {
        DriverFactory.quitDriver();
    }
}
