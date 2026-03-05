package com.fl.automation.tests;
import com.fl.automation.core.*;
import com.fl.automation.pages.*;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.*;

/** AC ID: 3195, TS ID: TS001, TC ID: TC003, Description: Launch app, open store locator, enter Boston MA, click search, verify store results displayed */
public class TS001_TC003_3195 {
    private WebDriver driver;
    @BeforeMethod
    public void setUp() {
        DriverFactory.initDriver();
        driver = DriverFactory.getDriver();
        driver.get(ConfigReader.get("base.url"));
    }
    @Test
    public void testStoreResultsDisplayed() {
        HomePage home = new HomePage(driver);
        home.clickFindAStore();
        StoreLocatorPage locator = new StoreLocatorPage(driver);
        locator.clickSelectMyStore();
        StoreResultsPage results = new StoreResultsPage(driver);
        results.enterLocation("Boston MA");
        results.clickSearch();
        Assert.assertTrue(results.getStoreCards().size() > 0, "Store results should be displayed");
    }
    @AfterMethod
    public void tearDown() {
        DriverFactory.quitDriver();
    }
}
