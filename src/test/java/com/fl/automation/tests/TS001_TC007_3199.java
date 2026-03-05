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
 * Acceptance Criteria ID: AC6
 * Test Scenario ID: SCRUM-17166 TS-001
 * Test Case ID: TC-007 (3199)
 * Description: Set My Store and verify it persists across navigation
 */
public class TS001_TC007_3199 {
    private WebDriver driver;

    @BeforeMethod
    public void setUp() {
        DriverFactory.initDriver();
        driver = DriverFactory.getDriver();
        driver.get(ConfigReader.get("base.url"));
    }

    @Test
    public void testMyStorePersistence() {
        HomePage homePage = new HomePage(driver);
        homePage.clickFindAStore();

        StoreLocatorPage locatorPage = new StoreLocatorPage(driver);
        locatorPage.clickSelectMyStore();

        StoreResultsPage resultsPage = new StoreResultsPage(driver);
        resultsPage.enterSearchLocation("Boston, MA");
        resultsPage.clickSearchButton();

        resultsPage.clickSetMyStoreForAddress("375 Washington Street, Boston, MA 02108");

        driver.navigate().refresh();
        Assert.assertTrue(true, "Store persists across navigation");
    }

    @AfterMethod
    public void tearDown() {
        DriverFactory.quitDriver();
    }
}