package com.fl.automation.tests;

import com.fl.automation.core.ConfigReader;
import com.fl.automation.core.DriverFactory;
import com.fl.automation.pages.HomePage;
import com.fl.automation.pages.StoreLocatorPage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * AC ID: 1646
 * TS ID: TS-002
 * TC ID: TC-001
 * Description: Launch homepage, Click 'Find a Store', Click 'Select My Store' link, Verify popup window opens with Location textbox and Search button
 */
public class TS002_TC001_ {
    private static final Logger logger = LogManager.getLogger(TS002_TC001_.class);
    private WebDriver driver;

    @BeforeMethod
    public void setUp() {
        DriverFactory.initDriver();
        driver = DriverFactory.getDriver();
        driver.get(ConfigReader.getProperty("base.url"));
        logger.info("Navigated to Foot Locker homepage");
    }

    @Test
    public void testSelectMyStorePopup() {
        HomePage homePage = new HomePage();
        homePage.clickFindAStore();
        StoreLocatorPage locatorPage = new StoreLocatorPage();
        Assert.assertTrue(locatorPage.isSelectMyStoreLinkDisplayed(), "Select My Store link not displayed");
        locatorPage.clickSelectMyStore();
        Assert.assertTrue(locatorPage.isLocationTextboxDisplayed(), "Location textbox not displayed");
        logger.info("Verified Select My Store popup with location textbox");
    }

    @AfterMethod
    public void tearDown() {
        DriverFactory.quitDriver();
    }
}
