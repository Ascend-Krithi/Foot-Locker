package com.fl.automation.tests;

import com.fl.automation.core.ConfigReader;
import com.fl.automation.core.DriverFactory;
import com.fl.automation.pages.HomePage;
import com.fl.automation.pages.StoreLocatorPage;
import com.fl.automation.pages.StoreResultsPage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * TS ID: TS-007
 * TC ID: TC-001
 * Description: Verify store details
 */
public class TS007_TC001_ {
    private static final Logger logger = LogManager.getLogger(TS007_TC001_.class);
    private WebDriver driver;

    @BeforeMethod
    public void setUp() {
        DriverFactory.initDriver();
        driver = DriverFactory.getDriver();
        driver.get(ConfigReader.getProperty("base.url"));
        logger.info("Navigated to Foot Locker homepage");
    }

    @Test
    public void testVerifyStoreDetails() {
        HomePage homePage = new HomePage();
        homePage.clickFindAStore();
        StoreLocatorPage locatorPage = new StoreLocatorPage();
        locatorPage.clickSelectMyStore();
        locatorPage.enterLocation("Houston");
        locatorPage.clickSearchButton();
        StoreResultsPage resultsPage = new StoreResultsPage();
        Assert.assertTrue(resultsPage.areStoreCardsDisplayed(), "Store results not displayed");
        WebElement firstCard = resultsPage.getStoreCards().get(0);
        Assert.assertTrue(resultsPage.isStoreAddressDisplayed(firstCard), "Store address not displayed");
        logger.info("Verified store details for first result");
    }

    @AfterMethod
    public void tearDown() {
        DriverFactory.quitDriver();
    }
}
