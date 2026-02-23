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
 * Test Case ID: 2011
 * Test Case Name: Test Case - SCRUM-15408 TS-001 TC-001
 * Description: Launch the Foot Locker website homepage, click on 'Find a Store' link in the header,
 * and verify that a popup appears with 'Select My Store' link.
 */
public class TS001_TC001_TestCase_SCRUM15408_TS001_TC001 {

    private WebDriver driver;

    @BeforeMethod
    public void setUp() {
        DriverFactory.initDriver();
        driver = DriverFactory.getDriver();
        driver.get(ConfigReader.get("base.url"));
    }

    @Test
    public void testFindAStorePopupWithSelectMyStore() {
        HomePage homePage = new HomePage(driver);
        Assert.assertTrue(homePage.isFindAStoreDisplayed(), "Find a Store link not displayed on homepage");
        
        homePage.clickFindAStore();
        
        StoreLocatorPage locatorPage = new StoreLocatorPage(driver);
        Assert.assertTrue(locatorPage.isSelectMyStoreDisplayed(), "Select My Store link not displayed in popup");
    }

    @AfterMethod
    public void tearDown() {
        DriverFactory.quitDriver();
    }
}