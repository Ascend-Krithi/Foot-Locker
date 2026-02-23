package com.fl.automation.tests;

import com.fl.automation.core.DriverFactory;
import com.fl.automation.pages.HomePage;
import com.fl.automation.pages.StoreLocatorPage;
import com.fl.automation.pages.StoreResultsPage;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * Acceptance Criteria ID: SCRUM-15408
 * Test Scenario ID: TS-007
 * Test Case ID: TC-001
 * Description: Launch homepage, Click Find a Store, Click Select My Store, Enter 'Boston, MA', Click Search, Locate store '375 Washington Street, Boston, MA 02108', Click Set My Store, Navigate to another page, Verify store remains set
 */
public class TS007_TC001_TestCaseSCRUM15408TS007TC001 {
    @BeforeMethod
    public void setUp() {
        DriverFactory.initDriver();
    }

    @AfterMethod
    public void tearDown() {
        DriverFactory.quitDriver();
    }

    @Test
    public void testStoreRemainsSetAfterNavigation() {
        HomePage homePage = new HomePage();
        homePage.open();
        homePage.clickFindAStore();
        StoreLocatorPage locatorPage = new StoreLocatorPage();
        locatorPage.clickSelectMyStore();
        locatorPage.enterLocation("Boston, MA");
        locatorPage.clickSearch();
        StoreResultsPage resultsPage = new StoreResultsPage();
        Assert.assertTrue(resultsPage.clickSetMyStoreForAddress("375 Washington Street, Boston, MA 02108"),
                "Should be able to click Set My Store for the address");
        Assert.assertTrue(resultsPage.isConfirmationIndicatorDisplayed(),
                "Confirmation indicator should be displayed after setting store");
        // Navigate to homepage again
        homePage.open();
        // Reopen Find a Store and check confirmation
        homePage.clickFindAStore();
        locatorPage.clickSelectMyStore();
        Assert.assertTrue(resultsPage.isConfirmationIndicatorDisplayed(),
                "Store should remain set after navigation");
    }
}
