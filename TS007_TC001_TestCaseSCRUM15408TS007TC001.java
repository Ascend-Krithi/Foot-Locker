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
 * Acceptance Criteria ID: AC6
 * Test Scenario ID: SCRUM-15408 TS-007
 * Test Case ID: 2169
 * Description: Verify preferred store remains set after navigating to another page
 */
public class TS007_TC001_TestCaseSCRUM15408TS007TC001 {
    private HomePage homePage;
    private StoreLocatorPage storeLocatorPage;
    private StoreResultsPage storeResultsPage;

    @BeforeMethod
    public void setUp() {
        DriverFactory.initDriver();
        homePage = new HomePage();
        storeLocatorPage = new StoreLocatorPage();
        storeResultsPage = new StoreResultsPage();
    }

    @AfterMethod
    public void tearDown() {
        DriverFactory.quitDriver();
    }

    @Test
    public void testPreferredStorePersistsAfterNavigation() {
        homePage.open();
        homePage.clickFindAStore();
        homePage.clickSelectMyStore();
        storeLocatorPage.enterLocation("Boston, MA");
        storeLocatorPage.clickSearchButton();
        Assert.assertTrue(storeResultsPage.isStoreWithAddressVisible("375 Washington Street, Boston, MA 02108"), "Store with address should be visible");
        storeResultsPage.setMyStoreByAddress("375 Washington Street, Boston, MA 02108");
        DriverFactory.getDriver().navigate().to("https://www.footlocker.com/shop/men");
        Assert.assertTrue(storeResultsPage.isConfirmationIndicatorDisplayed("375 Washington Street, Boston, MA 02108"), "Preferred store should remain set and displayed");
    }
}
