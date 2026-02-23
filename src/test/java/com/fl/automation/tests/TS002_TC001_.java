package com.fl.automation.tests;

import com.fl.automation.core.DriverFactory;
import com.fl.automation.pages.HomePage;
import com.fl.automation.pages.StoreLocatorPage;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * Acceptance Criteria ID: 2164
 * Test Scenario ID: SCRUM-15408 TS-002
 * Test Case ID: TC-001
 * Description: Launch website -> Click 'Find a Store' -> Click 'Select My Store'
 * Expected: Homepage loads, popup appears, Find a Store popup opens with Location textbox and Search for Stores button
 */
public class TS002_TC001_ {
    private HomePage homePage;
    private StoreLocatorPage storeLocatorPage;

    @BeforeMethod
    public void setUp() {
        homePage = new HomePage();
        storeLocatorPage = new StoreLocatorPage();
        homePage.launch();
    }

    @Test
    public void testFindAStorePopupOpens() {
        homePage.clickFindAStore();
        homePage.clickSelectMyStore();
        Assert.assertTrue(storeLocatorPage.isLocationTextboxDisplayed(), "Location textbox not displayed");
        Assert.assertTrue(storeLocatorPage.isSearchButtonDisplayed(), "Search for Stores button not displayed");
    }

    @AfterMethod
    public void tearDown() {
        DriverFactory.quitDriver();
    }
}
