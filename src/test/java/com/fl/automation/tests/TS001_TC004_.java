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
Acceptance Criteria ID: 3196
Test Scenario ID: SCRUM-17166 TS-001
Test Case ID: TC-004
Description: Launch homepage, click Find a Store, click Select My Store, enter Boston, MA, click Search for Stores, verify store with address 375 Washington Street, Boston, MA 02108 is visible
*/
public class TS001_TC004_ {
    private WebDriver driver;
    @BeforeMethod
    public void setUp() {
        DriverFactory.initDriver();
        driver = DriverFactory.getDriver();
        driver.get(ConfigReader.get("base.url"));
    }
    @Test
    public void testStoreWithSpecificAddressVisible() {
        HomePage homePage = new HomePage(driver);
        homePage.clickFindAStore();
        StoreLocatorPage locatorPage = new StoreLocatorPage(driver);
        locatorPage.clickSelectMyStore();
        StoreResultsPage resultsPage = new StoreResultsPage(driver);
        resultsPage.enterLocation("Boston, MA");
        resultsPage.clickSearchButton();
        Assert.assertTrue(resultsPage.isStoreWithAddressPresent("375 Washington Street, Boston, MA 02108"), "Store with address not found in results");
    }
    @AfterMethod
    public void tearDown() {
        DriverFactory.quitDriver();
    }
}
