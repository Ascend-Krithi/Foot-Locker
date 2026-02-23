package com.fl.automation.tests;

import com.fl.automation.core.DriverFactory;
import com.fl.automation.pages.HomePage;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * Acceptance Criteria ID: AC1
 * Test Scenario ID: SCRUM-15408 TS-001
 * Test Case ID: 2163
 * Description: Verify Find a Store popup and message on homepage
 */
public class TS001_TC001_TestCaseSCRUM15408TS001TC001 {
    private HomePage homePage;

    @BeforeMethod
    public void setUp() {
        DriverFactory.initDriver();
        homePage = new HomePage();
    }

    @AfterMethod
    public void tearDown() {
        DriverFactory.quitDriver();
    }

    @Test
    public void testFindAStorePopupAndMessage() {
        homePage.open();
        homePage.clickFindAStore();
        Assert.assertTrue(homePage.isFindAStorePopupDisplayed(), "Find a Store popup should be displayed");
        Assert.assertTrue(homePage.isPopupMessageDisplayed(), "Popup message should be displayed");
        Assert.assertTrue(homePage.isSelectMyStoreLinkDisplayed(), "Select My Store link should be visible");
    }
}
