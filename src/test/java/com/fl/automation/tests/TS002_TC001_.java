package com.fl.automation.tests;

import com.fl.automation.core.ConfigReader;
import com.fl.automation.core.DriverFactory;
import com.fl.automation.pages.HomePage;
import com.fl.automation.pages.StoreLocatorPage;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * AC ID: SCRUM-15408
 * TS ID: TS-002
 * TC ID: TC-001
 * Description: Click 'Select My Store' in popup, verify location textbox and search button
 */
public class TS002_TC001_ {
    private WebDriver driver;

    @BeforeMethod
    public void setUp() {
        DriverFactory.initDriver();
        driver = DriverFactory.getDriver();
        driver.get(ConfigReader.get("base.url"));
    }

    @Test
    public void testSelectMyStorePopup() {
        HomePage homePage = new HomePage(driver);
        StoreLocatorPage locatorPage = new StoreLocatorPage(driver);
        homePage.clickFindAStore();
        locatorPage.clickSelectMyStore();
        Assert.assertTrue(locatorPage.isSelectMyStoreLinkDisplayed(), "Location textbox or search button should be visible");
    }

    @AfterMethod
    public void tearDown() {
        DriverFactory.quitDriver();
    }
}
