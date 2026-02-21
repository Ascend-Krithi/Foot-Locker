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
 * TS ID: TS-008
 * TC ID: TC-001
 * Description: Test empty results
 */
public class TS008_TC001_ {
    private static final Logger logger = LogManager.getLogger(TS008_TC001_.class);
    private WebDriver driver;

    @BeforeMethod
    public void setUp() {
        DriverFactory.initDriver();
        driver = DriverFactory.getDriver();
        driver.get(ConfigReader.getProperty("base.url"));
        logger.info("Navigated to Foot Locker homepage");
    }

    @Test
    public void testEmptyResults() {
        HomePage homePage = new HomePage();
        homePage.clickFindAStore();
        StoreLocatorPage locatorPage = new StoreLocatorPage();
        locatorPage.clickSelectMyStore();
        locatorPage.enterLocation("ZZZZZZZZ"); // unlikely to match any store
        locatorPage.clickSearchButton();
        StoreResultsPage resultsPage = new StoreResultsPage();
        Assert.assertTrue(resultsPage.isEmptyResultsDisplayed(), "Empty results message not displayed");
        logger.info("Verified empty results message");
    }

    @AfterMethod
    public void tearDown() {
        DriverFactory.quitDriver();
    }
}
