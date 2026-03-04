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
 * Test Case ID: 2935
 * Test Case Name: Test Case - SCRUM-17166 TS-001 TC-003
 * Description: Search for stores in 'Boston, MA' using Store Locator popup, check the list of store results for the address '375 Washington Street, Boston, Massachusetts, 02108', and verify store with address '375 Washington Street, Boston, Massachusetts, 02108' is present in the results and matches exactly with the expected location.
 */
public class TS001_TC003_2935 {

    private WebDriver driver;

    @BeforeMethod
    public void setUp() {
        DriverFactory.initDriver();
        driver = DriverFactory.getDriver();
        driver.get(ConfigReader.get("base.url"));
    }

    @Test
    public void testVerifyExactStoreAddress() {
        HomePage homePage = new HomePage(driver);
        homePage.clickFindAStore();

        StoreLocatorPage locatorPage = new StoreLocatorPage(driver);
        locatorPage.clickSelectMyStore();

        StoreResultsPage resultsPage = new StoreResultsPage(driver);
        resultsPage.enterLocation("Boston, MA");
        resultsPage.clickSearchButton();

        Assert.assertTrue(resultsPage.isStoreAddressPresent("375 Washington Street, Boston, Massachusetts, 02108"), "Store address 375 Washington Street, Boston, Massachusetts, 02108 not found in results");
    }

    @AfterMethod
    public void tearDown() {
        DriverFactory.quitDriver();
    }
}