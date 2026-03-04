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
 * Test Case ID: 2904
 * Test Case Name: Test Case - SCRUM-17166 TS-003 TC-001
 * Description: Launch the Foot Locker homepage, click the 'Find a Store' link/button, click the 'Select My Store' link in the popup, enter 'Boston, MA' in the 'Location' textbox, click the 'Search for Stores' button, review the list of store results, and verify store with address '375 Washington Street, Boston, MA 02108' is present and address matches exactly.
 */
public class TS003_TC001_2904 {

    private WebDriver driver;

    @BeforeMethod
    public void setUp() {
        DriverFactory.initDriver();
        driver = DriverFactory.getDriver();
        driver.get(ConfigReader.get("base.url"));
    }

    @Test
    public void testVerifySpecificStoreAddressInResults() {
        HomePage homePage = new HomePage(driver);
        homePage.clickFindAStore();

        StoreLocatorPage locatorPage = new StoreLocatorPage(driver);
        locatorPage.clickSelectMyStore();

        StoreResultsPage resultsPage = new StoreResultsPage(driver);
        resultsPage.enterLocation("Boston, MA");
        resultsPage.clickSearchButton();

        Assert.assertTrue(resultsPage.isStoreAddressPresent("375 Washington Street, Boston, MA 02108"), "Store address 375 Washington Street, Boston, MA 02108 not found in results");
    }

    @AfterMethod
    public void tearDown() {
        DriverFactory.quitDriver();
    }
}