package com.fl.automation.tests;

import com.fl.automation.core.ConfigReader;
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
 * Acceptance Criteria ID: 2170
 * Test Scenario ID: SCRUM-15408 TS-008
 * Test Case ID: TC-001
 * Description: Launch homepage -> Click Find a Store -> Click Select My Store -> Enter Atlantis (invalid location) -> Click Search for Stores -> Verify No stores found message
 */
public class TS008_TC001_TestCase_SCRUM15408_TS008_TC001 {
    private WebDriver driver;
    private HomePage homePage;
    private StoreLocatorPage storeLocatorPage;
    private StoreResultsPage storeResultsPage;

    @BeforeMethod
    public void setUp() {
        DriverFactory.initDriver();
        driver = DriverFactory.getDriver();
        driver.get(ConfigReader.get("base.url"));
        homePage = new HomePage(driver);
        storeLocatorPage = new StoreLocatorPage(driver);
        storeResultsPage = new StoreResultsPage(driver);
    }

    @Test
    public void testInvalidLocationShowsNoStoresFound() {
        homePage.clickFindAStore();
        storeLocatorPage.clickSelectMyStore();
        storeLocatorPage.enterLocation("Atlantis");
        storeLocatorPage.clickSearchForStores();
        Assert.assertTrue(storeResultsPage.isNoStoresFoundMessageDisplayed(), "No stores found message not displayed for invalid location");
    }

    @AfterMethod
    public void tearDown() {
        DriverFactory.quitDriver();
    }
}
