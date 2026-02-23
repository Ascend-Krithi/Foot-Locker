package com.fl.automation.tests;

import com.fl.automation.core.ConfigReader;
import com.fl.automation.core.DriverFactory;
import com.fl.automation.pages.HomePage;
import com.fl.automation.pages.StoreLocatorPage;
import com.fl.automation.pages.StoreResultsPage;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
Acceptance Criteria ID: 2436
Test Scenario ID: SCRUM-15408 TS-004 TC-001
Test Case ID: 2436
Description: Search for 'Boston, MA' and verify store with address is present
*/
public class TS004_TC001_2436 {
    private WebDriver driver;

    @BeforeMethod
    public void setUp() {
        DriverFactory.initDriver("chrome");
        driver = DriverFactory.getDriver();
        driver.get(ConfigReader.get("base.url"));
    }

    @Test
    public void testBostonStoreAddressPresent() {
        HomePage homePage = new HomePage(driver);
        homePage.clickFindAStore();
        homePage.clickSelectMyStore();
        StoreLocatorPage locatorPage = new StoreLocatorPage(driver);
        locatorPage.enterLocation("Boston, MA");
        locatorPage.clickSearchForStores();
        StoreResultsPage resultsPage = new StoreResultsPage(driver);
        Assert.assertTrue(resultsPage.isStoreWithAddressPresent("375 Washington Street, Boston, MA 02108"), "Store with specified address is not present");
    }

    @AfterMethod
    public void tearDown() {
        DriverFactory.quitDriver();
    }
}
