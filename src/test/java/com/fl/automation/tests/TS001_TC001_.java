package com.fl.automation.tests;

import com.fl.automation.core.DriverFactory;
import com.fl.automation.pages.HomePage;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * Acceptance Criteria ID: 2163
 * Test Scenario ID: SCRUM-15408 TS-001
 * Test Case ID: TC-001
 * Description: Launch Foot Locker website -> Click 'Find a Store' -> Observe popup message and 'Select My Store' link
 * Expected: Homepage loads, popup appears, message 'Choose a preferred store to make shopping easier' and 'Select My Store' link visible
 */
public class TS001_TC001_ {
    private HomePage homePage;

    @BeforeMethod
    public void setUp() {
        homePage = new HomePage();
        homePage.launch();
    }

    @Test
    public void testFindAStorePopup() {
        homePage.clickFindAStore();
        Assert.assertTrue(homePage.isPopupMessageDisplayed(), "Popup message not displayed");
        Assert.assertTrue(homePage.isSelectMyStoreLinkDisplayed(), "'Select My Store' link not displayed");
    }

    @AfterMethod
    public void tearDown() {
        DriverFactory.quitDriver();
    }
}
