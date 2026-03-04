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
 * Test Case ID: 2907
 * Test Case Name: Test Case - SCRUM-17166 TS-006 TC-001
 * Description: Launch the Foot Locker homepage, click the 'Find a Store' link/button, click the 'Select My Store' link in the popup, enter 'Boston, MA' in the 'Location' textbox, click the 'Search for Stores' button, locate the store with address '375 Washington Street, Boston, MA 02108', click the 'Set My Store' button for this store, navigate to another page (e.g., product page), and verify the selected store remains set as preferred.
 */
public class TS006_TC001_2907 {

    private WebDriver driver;

    @BeforeMethod
    public void setUp() {
        DriverFactory.initDriver();
        driver = DriverFactory.getDriver();
        driver.get(ConfigReader.get("base.url"));
    }

    @Test
    public void testStoreSelectionPersistsOnNavigation() {
        HomePage homePage = new HomePage(driver);
        homePage.clickFindAStore();

        StoreLocatorPage locatorPage = new StoreLocatorPage(driver);
        locatorPage.clickSelectMyStore();

        StoreResultsPage resultsPage = new StoreResultsPage(driver);
        resultsPage.enterLocation("Boston, MA");
        resultsPage.clickSearchButton();

        resultsPage.clickSetMyStoreForAddress("375 Washington Street, Boston, MA 02108");

        driver.navigate().to(ConfigReader.get("base.url") + "/category/mens/shoes.html");

        Assert.assertTrue(resultsPage.isStoreSetConfirmationDisplayed(), "Selected store not persisted after navigation");
    }

    @AfterMethod
    public void tearDown() {
        DriverFactory.quitDriver();
    }
}