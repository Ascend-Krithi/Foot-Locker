package com.fl.automation.tests;

import com.fl.automation.core.ConfigReader;
import com.fl.automation.core.DriverFactory;
import com.fl.automation.pages.HomePage;
import com.fl.automation.pages.StoreLocatorPage;
import com.fl.automation.pages.StoreResultsPage;
import org.testng.Assert;
import org.testng.annotations.*;

/**
 * Acceptance Criteria ID: 2170
 * Test Scenario ID: SCRUM-15408 TS-008
 * Test Case ID: TC-001
 * Description: Launch website -> Click Find a Store -> Click Select My Store -> Enter 'Atlantis' in Location textbox -> Click Search for Stores button
 */
public class TS008_TC001_ {
    @BeforeMethod
    public void setUp() {
        DriverFactory.initDriver();
        DriverFactory.getDriver().get(ConfigReader.get("baseUrl"));
    }
    @Test
    public void testNoStoresFoundForAtlantis() {
        HomePage home = new HomePage();
        home.clickFindAStore();
        StoreLocatorPage locator = new StoreLocatorPage();
        locator.clickSelectMyStore();
        StoreResultsPage results = new StoreResultsPage();
        results.enterLocation("Atlantis");
        results.clickSearch();
        Assert.assertTrue(results.isNoStoresFoundMessageDisplayed(), "'No stores found' message should be displayed");
    }
    @AfterMethod
    public void tearDown() {
        DriverFactory.quitDriver();
    }
}
