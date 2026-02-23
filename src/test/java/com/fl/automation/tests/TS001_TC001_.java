package com.fl.automation.tests;

import com.fl.automation.core.ConfigReader;
import com.fl.automation.core.DriverFactory;
import com.fl.automation.pages.HomePage;
import com.fl.automation.pages.StoreLocatorPage;
import org.testng.Assert;
import org.testng.annotations.*;

/**
 * Acceptance Criteria ID: 2163
 * Test Scenario ID: SCRUM-15408 TS-001
 * Test Case ID: TC-001
 * Description: Launch Foot Locker website -> Click Find a Store -> Observe popup message and Select My Store link
 */
public class TS001_TC001_ {
    @BeforeMethod
    public void setUp() {
        DriverFactory.initDriver();
        DriverFactory.getDriver().get(ConfigReader.get("baseUrl"));
    }
    @Test
    public void testFindAStorePopup() {
        HomePage home = new HomePage();
        home.clickFindAStore();
        StoreLocatorPage locator = new StoreLocatorPage();
        Assert.assertTrue(locator.isPopupDisplayed(), "Popup should be displayed");
        Assert.assertTrue(locator.isPopupMessageDisplayed(), "Popup message should be displayed");
        Assert.assertTrue(locator.isSelectMyStoreLinkVisible(), "Select My Store link should be visible");
    }
    @AfterMethod
    public void tearDown() {
        DriverFactory.quitDriver();
    }
}
