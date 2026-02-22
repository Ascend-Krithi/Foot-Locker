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
 * Acceptance Criteria ID: 
 * Test Scenario ID: SCRUM-15408 TS-001
 * Test Case ID: TC-014
 * Description: Launch the Foot Locker website homepage, click Find a Store in the header, click Select My Store in the popup, enter Boston, MA in the Location textbox, click the Search for Stores button, click Set My Store for the store at 375 Washington Street, Boston, MA 02108, and observe the confirmation indicator (message, highlight, or store name) in the UI.
 */
public class TS001_TC014_VerifyStoreConfirmation {
    private WebDriver driver;

    @BeforeMethod
    public void setUp() {
        DriverFactory.initDriver();
        driver = DriverFactory.getDriver();
        driver.get(ConfigReader.get("base.url"));
    }

    @Test
    public void testVerifyStoreConfirmation() {
        HomePage homePage = new HomePage(driver);
        homePage.clickFindAStore();

        StoreLocatorPage locatorPage = new StoreLocatorPage(driver);
        locatorPage.clickSelectMyStore();

        StoreResultsPage resultsPage = new StoreResultsPage(driver);
        resultsPage.enterSearchLocation("Boston, MA");
        resultsPage.clickSearchButton();

        resultsPage.clickSetMyStoreForAddress("375 Washington Street, Boston, MA 02108");
        Assert.assertTrue(resultsPage.isStoreSetConfirmationDisplayed(), "Confirmation indicator is not displayed and store is not marked as My Store");
    }

    @AfterMethod
    public void tearDown() {
        DriverFactory.quitDriver();
    }
}