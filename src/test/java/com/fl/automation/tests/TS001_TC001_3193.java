package com.fl.automation.tests;
import com.fl.automation.core.*;
import com.fl.automation.pages.*;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.*;

/** AC ID: 3193, TS ID: TS001, TC ID: TC001, Description: Launch app, click Find a Store, verify popup with Select My Store link visible */
public class TS001_TC001_3193 {
    private WebDriver driver;
    @BeforeMethod
    public void setUp() {
        DriverFactory.initDriver();
        driver = DriverFactory.getDriver();
        driver.get(ConfigReader.get("base.url"));
    }
    @Test
    public void testFindAStorePopup() {
        HomePage home = new HomePage(driver);
        home.clickFindAStore();
        StoreLocatorPage locator = new StoreLocatorPage(driver);
        Assert.assertTrue(BrowserUtils.isDisplayed(driver, org.openqa.selenium.By.xpath("//a[contains(.,'Select My Store')]")), "Select My Store link should be visible");
    }
    @AfterMethod
    public void tearDown() {
        DriverFactory.quitDriver();
    }
}
