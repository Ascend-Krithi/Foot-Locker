package com.fl.automation.tests;
import com.fl.automation.core.*;
import com.fl.automation.pages.*;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.*;

/** AC ID: 3230, TS ID: TS004, TC ID: TC001, Description: Launch app, search Boston MA, verify exact store 375 Washington Street Boston MA 02108 exists */
public class TS004_TC001_3230 {
    private WebDriver driver;
    @BeforeMethod
    public void setUp() {
        DriverFactory.initDriver();
        driver = DriverFactory.getDriver();
        driver.get(ConfigReader.get("base.url"));
    }
    @Test
    public void testExactStoreExists() {
        HomePage home = new HomePage(driver);
        home.clickFindAStore();
        StoreLocatorPage locator = new StoreLocatorPage(driver);
        locator.clickSelectMyStore();
        StoreResultsPage results = new StoreResultsPage(driver);
        results.enterLocation("Boston MA");
        results.clickSearch();
        boolean found = false;
        for (org.openqa.selenium.WebElement card : results.getStoreCards()) {
            if (results.getStoreAddress(card).contains("375 Washington Street Boston MA 02108")) {
                found = true;
                break;
            }
        }
        Assert.assertTrue(found, "Exact store 375 Washington Street Boston MA 02108 should exist");
    }
    @AfterMethod
    public void tearDown() {
        DriverFactory.quitDriver();
    }
}
