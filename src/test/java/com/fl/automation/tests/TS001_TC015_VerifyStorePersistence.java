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
 * Test Case ID: TC-015
 * Description: Launch the Foot Locker website homepage, click Find a Store in the header, click Select My Store in the popup, enter Boston, MA in the Location textbox, click the Search for Stores button, click Set My Store for the store at 375 Washington Street, Boston, MA 02108, navigate to another page on the website, return to the homepage, click Find a Store again, and verify that 375 Washington Street, Boston, MA 02108 is still set and displayed as My Store.
 */
public class TS001_TC015_VerifyStorePersistence {
    private WebDriver driver;

    @BeforeMethod
    public void setUp() {
        DriverFactory.initDriver();
        driver = DriverFactory.getDriver();
        driver.get(ConfigReader.get("base.url"));
    }

    @Test
    public void testVerifyStorePersistence() {
        HomePage homePage = new HomePage(driver);
        homePage.clickFindAStore();

        StoreLocatorPage locatorPage = new StoreLocatorPage(driver);
        locatorPage.clickSelectMyStore();

        StoreResultsPage resultsPage = new StoreResultsPage(driver);
        resultsPage.enterSearchLocation("Boston, MA");
        resultsPage.clickSearchButton();

        resultsPage.clickSetMyStoreForAddress("375 Washington Street, Boston, MA 02108");

        driver.get(ConfigReader.get("base.url"));

        homePage.clickFindAStore();
        Assert.assertTrue(resultsPage.isStoreSetConfirmationDisplayed(), "Selected store does not persist and is not displayed as My Store across pages");
    }

    @AfterMethod
    public void tearDown() {
        DriverFactory.quitDriver();
    }
}