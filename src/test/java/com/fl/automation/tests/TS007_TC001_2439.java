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
Acceptance Criteria ID: 2439
Test Scenario ID: SCRUM-15408 TS-007 TC-001
Test Case ID: 2439
Description: Set My Store and verify persistence after navigation
*/
public class TS007_TC001_2439 {
    private WebDriver driver;

    @BeforeMethod
    public void setUp() {
        DriverFactory.initDriver("chrome");
        driver = DriverFactory.getDriver();
        driver.get(ConfigReader.get("base.url"));
    }

    @Test
    public void testMyStorePersistsAfterNavigation() {
        HomePage homePage = new HomePage(driver);
        homePage.clickFindAStore();
        homePage.clickSelectMyStore();
        StoreLocatorPage locatorPage = new StoreLocatorPage(driver);
        locatorPage.enterLocation("Boston, MA");
        locatorPage.clickSearchForStores();
        StoreResultsPage resultsPage = new StoreResultsPage(driver);
        resultsPage.setMyStore("375 Washington Street, Boston, MA 02108");
        homePage.navigateToAnotherPage();
        Assert.assertTrue(homePage.isMyStoreDisplayed("375 Washington Street, Boston, MA 02108"), "My Store is not persisted after navigation");
    }

    @AfterMethod
    public void tearDown() {
        DriverFactory.quitDriver();
    }
}
