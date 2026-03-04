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
 * Test Case ID: 2900
 * Test Case Name: Test Case - SCRUM-17166 TS-001 TC-001
 * Description: Launch the Foot Locker homepage in a browser, click the 'Find a Store' link/button, and observe the popup message and the presence of 'Select My Store' link.
 */
public class TS001_TC001_2900 {

    private WebDriver driver;

    @BeforeMethod
    public void setUp() {
        DriverFactory.initDriver();
        driver = DriverFactory.getDriver();
        driver.get(ConfigReader.get("base.url"));
    }

    @Test
    public void testFindAStorePopupAndSelectMyStoreLink() {
        HomePage homePage = new HomePage(driver);
        homePage.clickFindAStore();

        StoreLocatorPage locatorPage = new StoreLocatorPage(driver);
        Assert.assertTrue(locatorPage.isPopupMessageDisplayed(), "Popup message not displayed");
        Assert.assertTrue(locatorPage.isSelectMyStoreDisplayed(), "Select My Store link not displayed");
    }

    @AfterMethod
    public void tearDown() {
        DriverFactory.quitDriver();
    }
}