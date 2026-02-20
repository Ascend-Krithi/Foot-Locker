package com.fl.automation.tests;

import com.fl.automation.core.ConfigReader;
import com.fl.automation.core.DriverFactory;
import com.fl.automation.pages.HomePage;
import com.fl.automation.pages.StoreLocatorPage;
import com.fl.automation.pages.StoreResultsPage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * Acceptance Criteria ID: 1646
 * Test Scenario ID: SCRUM-15408 TS-002
 * Test Case ID: TC-001
 * Description: Launch homepage, click 'Find a Store', click 'Select My Store', verify popup with 'Location' textbox and 'Search for Stores' button
 */
public class TS002_TC001_ {
    private static final Logger logger = LogManager.getLogger(TS002_TC001_.class);
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
    public void testSelectMyStorePopup() {
        logger.info("Executing test: Click 'Find a Store', then 'Select My Store', verify location textbox and search button");
        homePage.clickFindAStore();
        Assert.assertTrue(storeLocatorPage.isPopupDisplayed(), "Popup with expected message is not displayed");
        storeLocatorPage.clickSelectMyStore();
        Assert.assertTrue(storeResultsPage.isLocationTextboxDisplayed(), "'Location' textbox is not displayed");
        Assert.assertTrue(storeResultsPage.isSearchForStoresButtonDisplayed(), "'Search for Stores' button is not displayed");
    }

    @AfterMethod
    public void tearDown() {
        DriverFactory.quitDriver();
    }
}
