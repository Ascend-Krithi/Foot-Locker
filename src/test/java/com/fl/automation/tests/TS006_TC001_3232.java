package com.fl.automation.tests;
import com.fl.automation.core.*;
import com.fl.automation.pages.*;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.*;

/** AC ID: 3232, TS ID: TS006, TC ID: TC001, Description: Launch app, set store 375 Washington Street, verify confirmation and consistency across pages */
public class TS006_TC001_3232 {
    private WebDriver driver;
    @BeforeMethod
    public void setUp() {
        DriverFactory.initDriver();
        driver = DriverFactory.getDriver();
        driver.get(ConfigReader.get("base.url"));
    }
    @Test
    public void testStoreConfirmationAndConsistency() {
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
        Assert.assertTrue(BrowserUtils.isDisplayed(driver, org.openqa.selenium.By.xpath("//div[contains(.,'Your store has been set')]")), "Confirmation should be displayed");
        // Navigate to another page
        driver.get(ConfigReader.get("base.url") + "/men");
        Assert.assertTrue(BrowserUtils.isDisplayed(driver, org.openqa.selenium.By.cssSelector(".c-store-indicator")), "Store should persist across pages");
    }
    @AfterMethod
    public void tearDown() {
        DriverFactory.quitDriver();
    }
}
