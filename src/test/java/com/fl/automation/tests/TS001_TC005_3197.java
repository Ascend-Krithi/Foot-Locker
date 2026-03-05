package com.fl.automation.tests;
import com.fl.automation.core.*;
import com.fl.automation.pages.*;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.*;

/** AC ID: 3197, TS ID: TS001, TC ID: TC005, Description: Launch app, search Boston MA, find store 375 Washington Street, click Set My Store */
public class TS001_TC005_3197 {
    private WebDriver driver;
    @BeforeMethod
    public void setUp() {
        DriverFactory.initDriver();
        driver = DriverFactory.getDriver();
        driver.get(ConfigReader.get("base.url"));
    }
    @Test
    public void testSetMyStore() {
        HomePage home = new HomePage(driver);
        home.clickFindAStore();
        StoreLocatorPage locator = new StoreLocatorPage(driver);
        locator.clickSelectMyStore();
        StoreResultsPage results = new StoreResultsPage(driver);
        results.enterLocation("Boston MA");
        results.clickSearch();
        boolean found = false;
        for (org.openqa.selenium.WebElement card : results.getStoreCards()) {
            if (results.getStoreAddress(card).contains("375 Washington Street")) {
                results.clickSetMyStore(card);
                found = true;
                break;
            }
        }
        Assert.assertTrue(found, "Set My Store button should be clicked for 375 Washington Street");
    }
    @AfterMethod
    public void tearDown() {
        DriverFactory.quitDriver();
    }
}
