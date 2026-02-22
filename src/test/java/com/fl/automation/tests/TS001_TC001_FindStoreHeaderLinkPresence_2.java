package com.fl.automation.tests;

import com.fl.automation.core.DriverFactory;
import com.fl.automation.pages.HomePage;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * Acceptance Criteria ID: 1934
 * Test Scenario ID: SCRUM-15408 TS-001
 * Test Case ID: TC-001
 * Description: Duplicate: Verify 'Find a Store' header link is present on Home Page
 */
public class TS001_TC001_FindStoreHeaderLinkPresence_2 {
    @BeforeMethod
    public void setUp() {
        DriverFactory.initDriver();
    }

    @Test
    public void testFindStoreHeaderLinkPresence() {
        HomePage homePage = new HomePage();
        homePage.open();
        Assert.assertTrue(homePage.isFindStoreHeaderDisplayed(), "Find a Store header link should be displayed");
    }

    @AfterMethod
    public void tearDown() {
        DriverFactory.quitDriver();
    }
}
