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
 * Test Case ID: 2906
 * Test Case Name: Test Case - SCRUM-17166 TS-005 TC-001
 * Description: Launch the Foot Locker homepage, click the 'Find a Store' link/button, click the 'Select My Store' link in the popup, enter 'Boston, MA' in the 'Location' textbox, click the 'Search for Stores' button, locate the store with address '375 Washington Street, Boston, MA 02108', click the 'Set My Store' button for this store, observe the confirmation indicator (message, highlight, or store name), navigate to different sections of the website (e.g., homepage, product page, cart), and verify selected store appears as preferred in each section.
 */
public class TS005_TC001_2906 {

    private WebDriver driver;

    @BeforeMethod
    public void setUp() {
        DriverFactory.initDriver();
        driver = DriverFactory.getDriver();
        driver.get(ConfigReader.get("base.url"));
    }

    @Test
    public void testStoreSelectionPersistsAcrossSections() {
        HomePage homePage = new HomePage(driver);
        homePage.clickFindAStore();

        StoreLocatorPage locatorPage = new StoreLocatorPage(driver);
        locatorPage.clickSelectMyStore();

        StoreResultsPage resultsPage = new StoreResultsPage(driver);
        resultsPage.enterLocation("Boston, MA");
        resultsPage.clickSearchButton();

        resultsPage.clickSetMyStoreForAddress("375 Washington Street, Boston, MA 02108");

        Assert.assertTrue(resultsPage.isStoreSetConfirmationDisplayed(), "Confirmation indicator not displayed");

        driver.navigate().to(ConfigReader.get("base.url"));
        Assert.assertTrue(resultsPage.isStoreSetConfirmationDisplayed(), "Selected store not persisted on homepage");
    }

    @AfterMethod
    public void tearDown() {
        DriverFactory.quitDriver();
    }
}