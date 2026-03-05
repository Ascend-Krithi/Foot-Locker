package com.fl.automation.tests;
import com.fl.automation.core.*;
import com.fl.automation.pages.*;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.*;

/** AC ID: 3229, TS ID: TS003, TC ID: TC001, Description: Launch app, open store locator, search Boston MA, verify store list displayed */
public class TS003_TC001_3229 {
    private WebDriver driver;
    @BeforeMethod
    public void setUp() {
        DriverFactory.initDriver();
        driver = DriverFactory.getDriver();
        driver.get(ConfigReader.get("base.url"));
    }
    @Test
    public void testStoreListDisplayed() {
        HomePage home = new HomePage(driver);
        home.clickFindAStore();
        StoreLocatorPage locator = new StoreLocatorPage(driver);
        locator.clickSelectMyStore();
        StoreResultsPage results = new StoreResultsPage(driver);
        results.enterLocation("Boston MA");
        results.clickSearch();
        Assert.assertTrue(results.getStoreCards().size() > 0, "Store list should be displayed");
    }
    @AfterMethod
    public void tearDown() {
        DriverFactory.quitDriver();
    }
}
