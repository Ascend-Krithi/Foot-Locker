package com.fl.automation.tests;

import com.fl.automation.core.DriverFactory;
import com.fl.automation.pages.HomePage;
import com.fl.automation.pages.StoreLocatorPage;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * Acceptance Criteria ID: 2171
 * Test Scenario ID: SCRUM-15408 TS-009
 * Test Case ID: TC-001
 * Description: Launch website -> Click 'Find a Store' -> Click 'Select My Store' -> Leave Location textbox empty -> Observe Search for Stores button
 * Expected: Search for Stores button is disabled
 */
public class TS009_TC001_ {
    private HomePage homePage;
    private StoreLocatorPage storeLocatorPage;

    @BeforeMethod
    public void setUp() {
        homePage = new HomePage();
        storeLocatorPage = new StoreLocatorPage();
        homePage.launch();
    }

    @Test
    public void testSearchButtonDisabledWhenLocationEmpty() {
        homePage.clickFindAStore();
        homePage.clickSelectMyStore();
        Assert.assertFalse(storeLocatorPage.isSearchButtonEnabled(), "Search for Stores button should be disabled when location is empty");
    }

    @AfterMethod
    public void tearDown() {
        DriverFactory.quitDriver();
    }
}
