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
 * Acceptance Criteria ID: 2168
 * Test Scenario ID: SCRUM-15408 TS-006
 * Test Case ID: TC-001
 * Description: Launch website -> Click 'Find a Store' -> Click 'Select My Store' -> Enter 'Boston, MA' -> Click 'Search for Stores' -> Locate store '375 Washington Street, Boston, MA 02108' -> Click 'Set My Store' -> Observe confirmation indicator
 * Expected: Confirmation indicator displayed and store appears consistently
 */
public class TS006_TC001_ {
    private HomePage homePage;
    private StoreLocatorPage storeLocatorPage;
    private StoreResultsPage storeResultsPage;

    @BeforeMethod
    public void setUp() {
        homePage = new HomePage();
        storeLocatorPage = new StoreLocatorPage();
        storeResultsPage = new StoreResultsPage();
        homePage.launch();
    }

    @Test
    public void testSetMyStoreConfirmation() {
        homePage.clickFindAStore();
        homePage.clickSelectMyStore();
        storeLocatorPage.enterLocation("Boston, MA");
        storeLocatorPage.clickSearchForStores();
        Assert.assertTrue(storeResultsPage.isStoreCardWithAddressDisplayed("375 Washington Street, Boston, MA 02108"), "Store with address not found");
        storeResultsPage.setMyStoreByAddress("375 Washington Street, Boston, MA 02108");
        Assert.assertTrue(storeResultsPage.isConfirmationIndicatorDisplayed(), "Confirmation indicator not displayed");
    }

    @AfterMethod
    public void tearDown() {
        DriverFactory.quitDriver();
    }
}
