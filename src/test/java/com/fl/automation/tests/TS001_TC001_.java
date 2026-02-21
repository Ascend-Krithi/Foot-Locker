package com.fl.automation.tests;

import com.fl.automation.core.ConfigReader;
import com.fl.automation.core.DriverFactory;
import com.fl.automation.pages.HomePage;
import com.fl.automation.pages.StoreLocatorPage;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * AC ID: SCRUM-15408
 * TS ID: TS-001
 * TC ID: TC-001
 * Description: Launch homepage, click 'Find a Store', verify popup and message
 */
public class TS001_TC001_ {
    private WebDriver driver;

    @BeforeMethod
    public void setUp() {
        DriverFactory.initDriver();
        driver = DriverFactory.getDriver();
        driver.get(ConfigReader.get("base.url"));
    }

    @Test
    public void testFindAStorePopupAppears() {
        HomePage homePage = new HomePage(driver);
        StoreLocatorPage locatorPage = new StoreLocatorPage(driver);
        homePage.clickFindAStore();
        Assert.assertTrue(locatorPage.isPopupDisplayed(), "Popup with message should appear");
        Assert.assertTrue(locatorPage.isSelectMyStoreLinkDisplayed(), "'Select My Store' link should be visible");
    }

    @AfterMethod
    public void tearDown() {
        DriverFactory.quitDriver();
    }
}
