package com.fl.automation.tests;

import com.fl.automation.core.DriverFactory;
import com.fl.automation.pages.HomePage;
import com.fl.automation.pages.StoreLocatorPage;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * Acceptance Criteria ID: AC2
 * Test Scenario ID: SCRUM-15408 TS-009
 * Test Case ID: 2171
 * Description: Verify Search for Stores button is disabled when location is empty
 */
public class TS009_TC001_TestCaseSCRUM15408TS009TC001 {
    private HomePage homePage;
    private StoreLocatorPage storeLocatorPage;

    @BeforeMethod
    public void setUp() {
        DriverFactory.initDriver();
        homePage = new HomePage();
        storeLocatorPage = new StoreLocatorPage();
    }

    @AfterMethod
    public void tearDown() {
        DriverFactory.quitDriver();
    }

    @Test
    public void testSearchButtonDisabledWhenLocationEmpty() {
        homePage.open();
        homePage.clickFindAStore();
        homePage.clickSelectMyStore();
        Assert.assertFalse(storeLocatorPage.isSearchButtonEnabled(), "Search for Stores button should be disabled when location is empty");
    }
}
