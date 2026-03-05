package com.fl.automation.tests;

import com.fl.automation.core.DriverFactory;
import com.fl.automation.pages.HomePage;
import com.fl.automation.pages.StoreLocatorPage;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * Acceptance Criteria ID: SCRUM-17166
 * Test Scenario ID: TS-001
 * Test Case ID: TC-001
 * Description: Launch homepage, click 'Find a Store', check for 'Select My Store' link
 */
public class TS001_TC001_ {
    @BeforeMethod
    public void setUp() {
        DriverFactory.getDriver();
    }

    @AfterMethod
    public void tearDown() {
        DriverFactory.quitDriver();
    }

    @Test
    public void testFindStorePopupAndSelectMyStoreLink() {
        HomePage home = new HomePage();
        home.launch();
        Assert.assertTrue(home.isLoaded(), "Homepage did not load");
        home.clickFindStore();
        StoreLocatorPage locator = new StoreLocatorPage();
        Assert.assertTrue(locator.isSelectMyStoreVisible(), "'Select My Store' link is not visible");
    }
}
