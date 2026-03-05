package com.fl.automation.tests;
import com.fl.automation.core.*;
import com.fl.automation.pages.*;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.*;

/** AC ID: 3231, TS ID: TS005, TC ID: TC001, Description: Launch app, search Boston MA, click Set My Store for 375 Washington Street */
public class TS005_TC001_3231 {
    private WebDriver driver;
    @BeforeMethod
    public void setUp() {
        DriverFactory.initDriver();
        driver = DriverFactory.getDriver();
        driver.get(ConfigReader.get("base.url"));
    }
    @Test
    public void testSetMyStoreForSpecificStore() {
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
        Assert.assertTrue(found, "Set My Store should be clicked for 375 Washington Street");
    }
    @AfterMethod
    public void tearDown() {
        DriverFactory.quitDriver();
    }
}
