package com.fl.automation.tests;
import com.fl.automation.core.*;
import com.fl.automation.pages.*;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.*;

/** AC ID: 3196, TS ID: TS001, TC ID: TC004, Description: Launch app, search Boston MA, verify store address contains 375 Washington Street */
public class TS001_TC004_3196 {
    private WebDriver driver;
    @BeforeMethod
    public void setUp() {
        DriverFactory.initDriver();
        driver = DriverFactory.getDriver();
        driver.get(ConfigReader.get("base.url"));
    }
    @Test
    public void testStoreAddressContainsWashingtonStreet() {
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
                found = true;
                break;
            }
        }
        Assert.assertTrue(found, "Store address should contain 375 Washington Street");
    }
    @AfterMethod
    public void tearDown() {
        DriverFactory.quitDriver();
    }
}
