package com.fl.automation.tests;

import com.fl.automation.core.DriverFactory;
import com.fl.automation.pages.HomePage;
import com.fl.automation.pages.StoreLocatorPage;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * Acceptance Criteria ID: 1935
 * Test Scenario ID: SCRUM-15408 TS-001
 * Test Case ID: TC-002
 * Description: Verify navigation to Store Locator page from Home Page
 */
public class TS001_TC002_NavigateToStoreLocator {
    @BeforeMethod
    public void setUp() {
        DriverFactory.initDriver();
    }

    @Test
    public void testNavigateToStoreLocator() {
        HomePage homePage = new HomePage();
        homePage.open();
        homePage.clickFindStoreHeader();
        StoreLocatorPage locatorPage = new StoreLocatorPage();
        Assert.assertTrue(locatorPage.isStoreResultDisplayed() || locatorPage.isEmptyResultsMessageDisplayed(),
                "Should be on Store Locator page with results or empty message");
    }

    @AfterMethod
    public void tearDown() {
        DriverFactory.quitDriver();
    }
}
