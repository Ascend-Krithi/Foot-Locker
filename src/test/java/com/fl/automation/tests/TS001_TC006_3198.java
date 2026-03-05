package com.fl.automation.tests;
import com.fl.automation.core.*;
import com.fl.automation.pages.*;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.*;

/** AC ID: 3198, TS ID: TS001, TC ID: TC006, Description: Launch app, search Boston MA, set store 375 Washington Street, verify confirmation displayed */
public class TS001_TC006_3198 {
    private WebDriver driver;
    @BeforeMethod
    public void setUp() {
        DriverFactory.initDriver();
        driver = DriverFactory.getDriver();
        driver.get(ConfigReader.get("base.url"));
    }
    @Test
    public void testSetStoreConfirmation() {
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
        // Assume confirmation is a popup or message, check for its presence
        Assert.assertTrue(BrowserUtils.isDisplayed(driver, org.openqa.selenium.By.xpath("//div[contains(.,'Your store has been set')]")), "Confirmation should be displayed");
    }
    @AfterMethod
    public void tearDown() {
        DriverFactory.quitDriver();
    }
}
