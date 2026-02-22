package com.fl.automation.tests;

import com.fl.automation.core.DriverFactory;
import com.fl.automation.pages.HomePage;
import com.fl.automation.pages.StoreLocatorPage;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * Acceptance Criteria ID: 1945
 * Test Scenario ID: SCRUM-15408 TS-001
 * Test Case ID: TC-005
 * Description: Duplicate: Search for a location with no stores and verify empty results message
 */
public class TS001_TC005_EmptyResultsMessage_2 {
    @BeforeMethod
    public void setUp() {
        DriverFactory.initDriver();
    }

    @Test
    public void testEmptyResultsMessage() {
        HomePage homePage = new HomePage();
        homePage.open();
        homePage.clickFindStoreHeader();
        StoreLocatorPage locatorPage = new StoreLocatorPage();
        locatorPage.enterSearchText("NowhereLand");
        Assert.assertTrue(locatorPage.isEmptyResultsMessageDisplayed(), "Empty results message should be displayed");
    }

    @AfterMethod
    public void tearDown() {
        DriverFactory.quitDriver();
    }
}
