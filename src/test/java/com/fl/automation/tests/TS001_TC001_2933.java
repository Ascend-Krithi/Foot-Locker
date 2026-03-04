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
 * Test Case ID: 2933
 * Test Case Name: Test Case - SCRUM-17166 TS-001 TC-001
 * Description: Launch the Foot Locker homepage and click on the 'Find a Store' link/button to verify Store Locator popup appears below 'Find a Store' displaying the message 'Choose a preferred store to make shopping easier' and a 'Select My Store' link.
 */
public class TS001_TC001_2933 {

    private WebDriver driver;

    @BeforeMethod
    public void setUp() {
        DriverFactory.initDriver();
        driver = DriverFactory.getDriver();
        driver.get(ConfigReader.get("base.url"));
    }

    @Test
    public void testStoreLocatorPopupDisplay() {
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