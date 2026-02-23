package com.fl.automation.tests;

import com.fl.automation.core.DriverFactory;
import com.fl.automation.pages.HomePage;
import com.fl.automation.pages.StoreLocatorPage;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * Acceptance Criteria ID: SCRUM-15408
 * Test Scenario ID: TS-002
 * Test Case ID: TC-001
 * Description: Launch homepage, Click Find a Store, Click Select My Store, Verify Location textbox and Search button
 */
public class TS002_TC001_TestCaseSCRUM15408TS002TC001 {
    @BeforeMethod
    public void setUp() {
        DriverFactory.initDriver();
    }

    @AfterMethod
    public void tearDown() {
        DriverFactory.quitDriver();
    }

    @Test
    public void testSelectMyStoreShowsLocationAndSearch() {
        HomePage homePage = new HomePage();
        homePage.open();
        homePage.clickFindAStore();
        StoreLocatorPage locatorPage = new StoreLocatorPage();
        locatorPage.clickSelectMyStore();
        Assert.assertTrue(locatorPage.isLocationTextboxDisplayed(), "Location textbox should be visible");
        Assert.assertTrue(locatorPage.isSearchButtonDisplayed(), "Search button should be visible");
    }
}
