package com.fl.automation.tests;

import com.fl.automation.core.ConfigReader;
import com.fl.automation.core.DriverFactory;
import com.fl.automation.pages.HomePage;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * Acceptance Criteria ID: 1646
 * Test Scenario ID: SCRUM-15408 TS-001
 * Test Case ID: TC-001
 * Description: Launch homepage, navigate to Store Locator, verify popup message and Select My Store link
 */
public class TS001_TC001_TestCase_SCRUM15408_TS001_TC001 {
    private HomePage homePage;

    @BeforeMethod
    public void setUp() {
        DriverFactory.initDriver();
        String baseUrl = ConfigReader.get("base.url");
        DriverFactory.getDriver().get(baseUrl);
        homePage = new HomePage();
    }

    @AfterMethod
    public void tearDown() {
        DriverFactory.quitDriver();
    }

    @Test(description = "SCRUM-15408 TS-001 TC-001: Launch homepage, navigate to Store Locator, verify popup message and Select My Store link")
    public void testStoreLocatorPopup() {
        // Step 1: Launch homepage (done in setUp)
        String actualUrl = DriverFactory.getDriver().getCurrentUrl();
        Assert.assertTrue(actualUrl.contains("footlocker.com"), "Homepage did not load successfully.");

        // Step 2: Click on the 'Find a Store' button
        homePage.clickFindAStore();

        // Step 3: Observe the popup message
        String popupMsg = homePage.getPopupMessage();
        Assert.assertEquals(popupMsg.trim(), "Choose a preferred store to make shopping easier", "Popup message mismatch.");

        // Step 4: Check for the presence of the 'Select My Store' link within the popup
        Assert.assertTrue(homePage.isSelectMyStoreVisible(), "'Select My Store' link is not visible in the popup.");
    }
}
