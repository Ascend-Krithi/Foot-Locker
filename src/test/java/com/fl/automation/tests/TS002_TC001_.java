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
 * Description: Launch homepage, click 'Find a Store', click 'Select My Store', verify store locator window
 */
public class TS002_TC001_ {
    @BeforeMethod
    public void setUp() {
        DriverFactory.initDriver();
    }

    @AfterMethod
    public void tearDown() {
        DriverFactory.quitDriver();
    }

    @Test
    public void testSelectMyStoreOpensLocator() {
        HomePage homePage = new HomePage();
        homePage.open();
        homePage.clickFindStore();
        Assert.assertTrue(homePage.isFindStorePopupDisplayed(), "Find a Store popup should be displayed");
        homePage.clickSelectMyStore();
        StoreLocatorPage locatorPage = new StoreLocatorPage();
        Assert.assertTrue(locatorPage.isSearchInputDisplayed(), "Store locator search input should be displayed");
    }
}
