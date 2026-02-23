package com.fl.automation.tests;

import com.fl.automation.core.DriverFactory;
import com.fl.automation.pages.HomePage;
import com.fl.automation.pages.StoreLocatorPage;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * Acceptance Criteria ID: AC1
 * Test Scenario ID: SCRUM-15408 TS-002
 * Test Case ID: 2164
 * Description: Verify Select My Store link opens Find a Store popup window
 */
public class TS002_TC001_TestCaseSCRUM15408TS002TC001 {
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
    public void testSelectMyStoreOpensPopupWindow() {
        homePage.open();
        homePage.clickFindAStore();
        homePage.clickSelectMyStore();
        Assert.assertTrue(storeLocatorPage.isLocationInputDisplayed(), "Location textbox should be visible");
        Assert.assertTrue(storeLocatorPage.isSearchButtonEnabled(), "Search for Stores button should be enabled");
    }
}
