package com.fl.automation.tests;

import com.fl.automation.core.ConfigReader;
import com.fl.automation.core.DriverFactory;
import com.fl.automation.pages.HomePage;
import com.fl.automation.pages.StoreLocatorPage;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * Acceptance Criteria ID: SCRUM-15408
 * Test Scenario ID: TS-001
 * Test Case ID: TC-001
 * Description: Launch homepage, click 'Find a Store', verify popup message and 'Select My Store' link
 */
public class TS001_TC001_ {
    @BeforeMethod
    public void setUp() {
        DriverFactory.initDriver();
    }

    @Test
    public void testFindAStorePopup() {
        HomePage home = new HomePage();
        StoreLocatorPage locator = new StoreLocatorPage();
        home.open(ConfigReader.get("base.url"));
        home.clickFindAStore();
        Assert.assertTrue(locator.isPopupMessageDisplayed(), "Popup message not displayed");
        Assert.assertTrue(locator.isSelectMyStoreLinkVisible(), "'Select My Store' link not visible");
    }

    @AfterMethod
    public void tearDown() {
        DriverFactory.quitDriver();
    }
}
