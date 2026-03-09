package com.fl.automation.tests;

import com.fl.automation.core.DriverFactory;
import com.fl.automation.core.ConfigReader;
import com.fl.automation.pages.HomePage;
import com.fl.automation.pages.StoreLocatorPage;
import com.fl.automation.pages.StoreResultsPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
Acceptance Criteria ID: 
Test Scenario ID: SCRUM-17166 TS-001
Test Case ID: TC-005 (3197)
Description: Search Boston MA, click Set My Store for 375 Washington Street
*/
public class TS001_TC005_3197 {
    private WebDriver driver;
    @BeforeMethod
    public void setUp() {
        DriverFactory.initDriver();
        driver = DriverFactory.getDriver();
        driver.get(ConfigReader.getProperty("base.url"));
    }
    @AfterMethod
    public void tearDown() {
        DriverFactory.quitDriver();
    }
    @Test
    public void testSetMyStoreForAddress() {
        HomePage home = new HomePage(driver);
        home.clickFindAStore();
        StoreLocatorPage locator = new StoreLocatorPage(driver);
        locator.clickSelectMyStore();
        StoreResultsPage results = new StoreResultsPage(driver);
        results.enterLocation("Boston MA");
        results.clickSearchButton();
        boolean found = false;
        for (WebElement card : results.getStoreResults()) {
            String addr = results.getStoreAddress(card);
            if (addr != null && addr.contains("375 Washington Street")) {
                results.clickSetMyStore(card);
                found = true;
                break;
            }
        }
        Assert.assertTrue(found, "Set My Store should be clicked for 375 Washington Street");
    }
}
