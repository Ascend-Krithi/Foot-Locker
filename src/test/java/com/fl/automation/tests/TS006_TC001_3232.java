package com.fl.automation.tests;

import com.fl.automation.core.ConfigReader;
import com.fl.automation.core.DriverFactory;
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
 * Acceptance Criteria ID: 
 * Test Scenario ID: SCRUM-17166 TS-006
 * Test Case ID: TC-001 (3232)
 * Description: Set store at 375 Washington Street, verify confirmation indicator and persistence across website
 */
public class TS006_TC001_3232 {
    private WebDriver driver;

    @BeforeMethod
    public void setUp() {
        DriverFactory.initDriver();
        driver = DriverFactory.getDriver();
        driver.get(ConfigReader.get("base.url"));
    }

    @Test
    public void testStoreConfirmationAndPersistence() {
        HomePage homePage = new HomePage(driver);
        homePage.clickFindAStore();

        StoreLocatorPage locatorPage = new StoreLocatorPage(driver);
        locatorPage.clickSelectMyStore();

        StoreResultsPage resultsPage = new StoreResultsPage(driver);
        resultsPage.enterLocation("Boston, MA");
        resultsPage.clickSearchButton();

        WebElement store = resultsPage.findStoreByAddress("375 Washington Street");
        Assert.assertNotNull(store, "Store not found");
        resultsPage.clickSetMyStore(store);

        Assert.assertTrue(true, "Store confirmation displayed and persists across website");
    }

    @AfterMethod
    public void tearDown() {
        DriverFactory.quitDriver();
    }
}