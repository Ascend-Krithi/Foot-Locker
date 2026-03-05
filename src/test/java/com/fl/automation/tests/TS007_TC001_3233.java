package com.fl.automation.tests;
import com.fl.automation.core.*;
import com.fl.automation.pages.*;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.*;

/** AC ID: 3233, TS ID: TS007, TC ID: TC001, Description: Launch app, set store, navigate to another page, verify store remains set */
public class TS007_TC001_3233 {
    private WebDriver driver;
    @BeforeMethod
    public void setUp() {
        DriverFactory.initDriver();
        driver = DriverFactory.getDriver();
        driver.get(ConfigReader.get("base.url"));
    }
    @Test
    public void testStoreRemainsSet() {
        HomePage home = new HomePage(driver);
        home.clickFindAStore();
        StoreLocatorPage locator = new StoreLocatorPage(driver);
        locator.clickSelectMyStore();
        StoreResultsPage results = new StoreResultsPage(driver);
        results.enterLocation("Boston MA");
        results.clickSearch();
        for (org.openqa.selenium.WebElement card : results.getStoreCards()) {
            if (results.getStoreAddress(card).contains("375 Washington Street")) {
                results.clickSetMyStore(card);
                break;
            }
        }
        // Navigate to another page
        driver.get(ConfigReader.get("base.url") + "/women");
        Assert.assertTrue(BrowserUtils.isDisplayed(driver, org.openqa.selenium.By.cssSelector(".c-store-indicator")), "Store should remain set after navigation");
    }
    @AfterMethod
    public void tearDown() {
        DriverFactory.quitDriver();
    }
}
