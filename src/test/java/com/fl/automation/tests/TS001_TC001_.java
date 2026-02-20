package com.fl.automation.tests;

import com.fl.automation.core.DriverFactory;
import com.fl.automation.pages.HomePage;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * Acceptance Criteria ID: SCRUM-15408
 * Test Scenario ID: TS-001
 * Test Case ID: TC-001
 * Description: Launch homepage, click 'Find a Store', verify popup and content
 */
public class TS001_TC001_ {
    @BeforeMethod
    public void setUp() {
        DriverFactory.initDriver();
    }

    @AfterMethod
    public void tearDown() {
        DriverFactory.quitDriver();
    }

    @Test
    public void testFindStorePopupAppears() {
        HomePage homePage = new HomePage();
        homePage.open();
        homePage.clickFindStore();
        Assert.assertTrue(homePage.isFindStorePopupDisplayed(), "Find a Store popup should be displayed");
        Assert.assertTrue(homePage.isSelectMyStoreLinkDisplayed(), "Select My Store link should be displayed in popup");
    }
}
