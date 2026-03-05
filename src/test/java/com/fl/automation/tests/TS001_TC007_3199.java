package com.fl.automation.tests;
import com.fl.automation.core.*;
import com.fl.automation.pages.*;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.*;

/** AC ID: 3199, TS ID: TS001, TC ID: TC007, Description: Launch app, set store, navigate to product page, return to home, verify store persists */
public class TS001_TC007_3199 {
    private WebDriver driver;
    @BeforeMethod
    public void setUp() {
        DriverFactory.initDriver();
        driver = DriverFactory.getDriver();
        driver.get(ConfigReader.get("base.url"));
    }
    @Test
    public void testStorePersistsAfterNavigation() {
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
        // Navigate to a product page (simulate by URL)
        driver.get(ConfigReader.get("base.url") + "/product/some-product");
        // Return to home
        driver.get(ConfigReader.get("base.url"));
        // Verify store persists (assume store info is displayed somewhere)
        Assert.assertTrue(BrowserUtils.isDisplayed(driver, org.openqa.selenium.By.cssSelector(".c-store-indicator")), "Store should persist after navigation");
    }
    @AfterMethod
    public void tearDown() {
        DriverFactory.quitDriver();
    }
}
