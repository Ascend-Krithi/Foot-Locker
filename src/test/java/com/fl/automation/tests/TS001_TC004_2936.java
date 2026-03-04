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
 * Test Case ID: 2936
 * Test Case Name: Test Case - SCRUM-17166 TS-001 TC-004
 * Description: Locate the store at '375 Washington Street, Boston, Massachusetts, 02108' in the search results, click 'Set My Store' for the store, and verify store is set as 'My Store' and saved as the user's preferred store.
 */
public class TS001_TC004_2936 {

    private WebDriver driver;

    @BeforeMethod
    public void setUp() {
        DriverFactory.initDriver();
        driver = DriverFactory.getDriver();
        driver.get(ConfigReader.get("base.url"));
    }

    @Test
    public void testSetMyStoreForBostonLocation() {
        HomePage homePage = new HomePage(driver);
        homePage.clickFindAStore();

        StoreLocatorPage locatorPage = new StoreLocatorPage(driver);
        locatorPage.clickSelectMyStore();

        StoreResultsPage resultsPage = new StoreResultsPage(driver);
        resultsPage.enterLocation("Boston, MA");
        resultsPage.clickSearchButton();

        resultsPage.clickSetMyStoreForAddress("375 Washington Street, Boston, Massachusetts, 02108");

        Assert.assertTrue(resultsPage.isStoreSetConfirmationDisplayed(), "Store not set as My Store");
    }

    @AfterMethod
    public void tearDown() {
        DriverFactory.quitDriver();
    }
}