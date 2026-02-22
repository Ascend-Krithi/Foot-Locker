package com.fl.automation.tests;

import com.fl.automation.core.DriverFactory;
import com.fl.automation.pages.HomePage;
import com.fl.automation.pages.StoreLocatorPage;
import com.fl.automation.pages.StoreResultsPage;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * Acceptance Criteria ID: 1947
 * Test Scenario ID: SCRUM-15408 TS-001
 * Test Case ID: TC-007
 * Description: Duplicate: Verify 'Set My Store' button is present in store result card
 */
public class TS001_TC007_SetMyStoreButtonPresence_2 {
    @BeforeMethod
    public void setUp() {
        DriverFactory.initDriver();
    }

    @Test
    public void testSetMyStoreButtonPresence() {
        HomePage homePage = new HomePage();
        homePage.open();
        homePage.clickFindStoreHeader();
        StoreLocatorPage locatorPage = new StoreLocatorPage();
        locatorPage.enterSearchText("Los Angeles");
        StoreResultsPage resultsPage = new StoreResultsPage();
        Assert.assertTrue(resultsPage.isSelectMyStoreDisplayed(), "Set My Store button/link should be present");
    }

    @AfterMethod
    public void tearDown() {
        DriverFactory.quitDriver();
    }
}
