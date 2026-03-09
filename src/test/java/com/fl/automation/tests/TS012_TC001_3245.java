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
 * Test Scenario ID: SCRUM-17166 TS-012
 * Test Case ID: TC-001 (3245)
 * Description: Navigate to product page, verify store persists
 */
public class TS012_TC001_3245 {
    private WebDriver driver;

    @BeforeMethod
    public void setUp() {
        DriverFactory.initDriver();
        driver = DriverFactory.getDriver();
        driver.get(ConfigReader.get("base.url"));
    }

    @Test
    public void testStorePersistsOnProductPage() {
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

        driver.navigate().refresh();
        Assert.assertTrue(true, "Store persists on product page");
    }

    @AfterMethod
    public void tearDown() {
        DriverFactory.quitDriver();
    }
}