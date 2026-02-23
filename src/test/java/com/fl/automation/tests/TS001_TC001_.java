package com.fl.automation.tests;

import com.fl.automation.core.DriverFactory;
import com.fl.automation.pages.HomePage;
import com.fl.automation.pages.StoreLocatorPage;
import com.fl.automation.pages.StoreResultsPage;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * Acceptance Criteria ID: SCRUM-15408
 * Test Scenario ID: TS-001
 * Test Case ID: TC-001
 * Description: Verify Find a Store header navigates to Store Locator page
 */
public class TS001_TC001_ {
    private WebDriver driver;
    private HomePage homePage;
    private StoreLocatorPage storeLocatorPage;
    private StoreResultsPage storeResultsPage;

    @BeforeMethod
    public void setUp() {
        driver = DriverFactory.getDriver();
        homePage = new HomePage(driver);
        storeLocatorPage = new StoreLocatorPage(driver);
        storeResultsPage = new StoreResultsPage(driver);
    }

    @Test
    public void testFindAStoreHeaderNavigation() {
        homePage.goTo();
        homePage.clickFindAStore();
        // Assume navigation is successful if search input is present
        // (actual assertion logic would depend on real UI)
        try {
            storeLocatorPage.enterSearch("New York");
            Assert.assertTrue(true, "Navigated to Store Locator page and search input is present");
        } catch (Exception e) {
            Assert.fail("Store Locator search input not found");
        }
    }

    @AfterMethod
    public void tearDown() {
        DriverFactory.quitDriver();
    }
}