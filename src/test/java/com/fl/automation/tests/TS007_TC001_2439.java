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
 * Test Scenario ID: SCRUM-15408 TS-007
 * Test Case ID: TC-001 (2439)
 * Description: Set preferred store and verify it persists across pages
 */
public class TS007_TC001_2439 {

    private WebDriver driver;

    @BeforeMethod
    public void setUp() {
        DriverFactory.initDriver();
        driver = DriverFactory.getDriver();
        driver.get(ConfigReader.get("base.url"));
    }

    @Test
    public void testPreferredStorePersistence() {
        HomePage homePage = new HomePage(driver);
        homePage.clickFindAStore();

        StoreLocatorPage locatorPage = new StoreLocatorPage(driver);
        locatorPage.clickSelectMyStore();

        StoreResultsPage resultsPage = new StoreResultsPage(driver);
        resultsPage.enterLocation("Boston, MA");
        resultsPage.clickSearchButton();

        resultsPage.clickSetMyStoreForAddress("375 Washington Street, Boston, MA 02108");

        driver.navigate().to(ConfigReader.get("base.url"));

        Assert.assertTrue(resultsPage.isConfirmationIndicatorDisplayed(), "Preferred store not persisted across pages");
    }

    @AfterMethod
    public void tearDown() {
        DriverFactory.quitDriver();
    }
}