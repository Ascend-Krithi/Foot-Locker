package com.fl.automation.tests;

import com.fl.automation.core.DriverFactory;
import com.fl.automation.core.ConfigReader;
import com.fl.automation.pages.StoreLocatorPage;
import com.fl.automation.pages.StoreResultsPage;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * Acceptance Criteria ID: SCRUM-17166
 * Test Scenario ID: TS-001
 * Test Case ID: TC-002 (2934)
 * Description: Open Store Locator popup -> Click Select My Store -> Enter Boston, MA -> Click Search -> Verify results
 */
public class TS001_TC002_2934 {
    private WebDriver driver;
    private StoreLocatorPage storeLocatorPage;
    private StoreResultsPage storeResultsPage;

    @BeforeMethod
    public void setUp() {
        driver = DriverFactory.getDriver();
        storeLocatorPage = new StoreLocatorPage(driver);
        storeResultsPage = new StoreResultsPage(driver);
    }

    @AfterMethod
    public void tearDown() {
        DriverFactory.quitDriver();
    }

    @Test
    public void testStoreLocatorPopupSearchBostonMA() {
        storeLocatorPage.clickSelectMyStore();
        storeLocatorPage.enterLocation("Boston, MA");
        storeLocatorPage.clickSearchButton();
        Assert.assertTrue(storeResultsPage.getStoreResultCards().size() > 0, "Store results should be displayed for Boston, MA");
    }
}