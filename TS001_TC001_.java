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
 * AC ID: 1645
 * TS ID: TS-001
 * TC ID: TC-001
 * Description: Launch Foot Locker homepage, Click 'Find a Store', Observe popup shows 'Choose a preferred store to make shopping easier' and 'Select My Store' link
 */
public class TS001_TC001_ {
    private static final Logger logger = LogManager.getLogger(TS001_TC001_.class);
    private WebDriver driver;

    @BeforeMethod
    public void setUp() {
        DriverFactory.initDriver();
        driver = DriverFactory.getDriver();
        driver.get(ConfigReader.getProperty("base.url"));
        logger.info("Navigated to Foot Locker homepage");
    }

    @Test
    public void testChoosePreferredStorePopup() {
        HomePage homePage = new HomePage();
        homePage.clickFindAStore();
        StoreLocatorPage locatorPage = new StoreLocatorPage();
        Assert.assertTrue(locatorPage.isChoosePreferredStorePopupDisplayed(), "Choose a preferred store popup not displayed");
        Assert.assertTrue(locatorPage.isSelectMyStoreLinkDisplayed(), "Select My Store link not displayed");
        logger.info("Verified popup and Select My Store link");
    }

    @AfterMethod
    public void tearDown() {
        DriverFactory.quitDriver();
    }
}
