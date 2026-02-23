package com.fl.automation.tests;

import com.fl.automation.core.ConfigReader;
import com.fl.automation.core.DriverFactory;
import com.fl.automation.pages.HomePage;
import com.fl.automation.pages.StoreLocatorPage;
import com.fl.automation.pages.StoreResultsPage;
import org.testng.Assert;
import org.testng.annotations.*;

/**
 * Acceptance Criteria ID: 2165
 * Test Scenario ID: SCRUM-15408 TS-003
 * Test Case ID: TC-001
 * Description: Launch website -> Click Find a Store -> Click Select My Store -> Enter 'Boston, MA' in Location textbox -> Click Search for Stores button
 */
public class TS003_TC001_ {
    @BeforeMethod
    public void setUp() {
        DriverFactory.initDriver();
        DriverFactory.getDriver().get(ConfigReader.get("baseUrl"));
    }
    @Test
    public void testSearchBostonStores() {
        HomePage home = new HomePage();
        home.clickFindAStore();
        StoreLocatorPage locator = new StoreLocatorPage();
        locator.clickSelectMyStore();
        StoreResultsPage results = new StoreResultsPage();
        Assert.assertTrue(results.isLocationInputDisplayed(), "Location textbox should be displayed");
        results.enterLocation("Boston, MA");
        results.clickSearch();
        Assert.assertTrue(results.isStoreResultDisplayed("Boston"), "Relevant store results in or near Boston should be displayed");
    }
    @AfterMethod
    public void tearDown() {
        DriverFactory.quitDriver();
    }
}
