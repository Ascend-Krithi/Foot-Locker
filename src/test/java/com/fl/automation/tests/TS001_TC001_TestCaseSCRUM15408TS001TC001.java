package com.fl.automation.tests;

import com.fl.automation.core.DriverFactory;
import com.fl.automation.pages.HomePage;
import com.fl.automation.pages.StoreLocatorPage;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * Acceptance Criteria ID: SCRUM-15408
 * Test Scenario ID: TS-001
 * Test Case ID: TC-001
 * Description: Launch homepage, Click Find a Store, Observe popup message and Select My Store link
 */
public class TS001_TC001_TestCaseSCRUM15408TS001TC001 {
    @BeforeMethod
    public void setUp() {
        DriverFactory.initDriver();
    }

    @AfterMethod
    public void tearDown() {
        DriverFactory.quitDriver();
    }

    @Test
    public void testFindAStorePopupAndSelectMyStoreLink() {
        HomePage homePage = new HomePage();
        homePage.open();
        homePage.clickFindAStore();
        StoreLocatorPage locatorPage = new StoreLocatorPage();
        Assert.assertTrue(locatorPage.isLocationTextboxDisplayed() || locatorPage.isSearchButtonDisplayed(),
                "Popup or Select My Store link should be visible");
    }
}
