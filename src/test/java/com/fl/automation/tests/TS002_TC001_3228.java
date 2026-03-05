package com.fl.automation.tests;
import com.fl.automation.core.*;
import com.fl.automation.pages.*;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.*;

/** AC ID: 3228, TS ID: TS002, TC ID: TC001, Description: Launch app, click Find a Store, click Select My Store, verify popup with Location and Search button */
public class TS002_TC001_3228 {
    private WebDriver driver;
    @BeforeMethod
    public void setUp() {
        DriverFactory.initDriver();
        driver = DriverFactory.getDriver();
        driver.get(ConfigReader.get("base.url"));
    }
    @Test
    public void testSelectMyStorePopupElements() {
        HomePage home = new HomePage(driver);
        home.clickFindAStore();
        StoreLocatorPage locator = new StoreLocatorPage(driver);
        locator.clickSelectMyStore();
        Assert.assertTrue(BrowserUtils.isDisplayed(driver, org.openqa.selenium.By.cssSelector("input[type='search']")), "Location textbox should be displayed");
        Assert.assertTrue(BrowserUtils.isDisplayed(driver, org.openqa.selenium.By.xpath("//button[contains(.,'Search')]")), "Search button should be displayed");
    }
    @AfterMethod
    public void tearDown() {
        DriverFactory.quitDriver();
    }
}
