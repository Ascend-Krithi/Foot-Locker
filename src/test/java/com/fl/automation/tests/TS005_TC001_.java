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
 * Acceptance Criteria ID: 1649
 * Test Scenario ID: SCRUM-15408 TS-005
 * Test Case ID: TC-001
 * Description: Verify store address details in results
 */
public class TS005_TC001_ {
    private WebDriver driver;

    @BeforeMethod
    public void setUp() {
        DriverFactory.initDriver();
        driver = DriverFactory.getDriver();
        driver.get(ConfigReader.get("base.url"));
    }

    @Test
    public void testStoreAddressDetails() {
        HomePage homePage = new HomePage(driver);
        homePage.clickFindAStore();
        StoreLocatorPage locatorPage = new StoreLocatorPage(driver);
        locatorPage.clickSelectMyStore();
        StoreResultsPage resultsPage = new StoreResultsPage(driver);
        resultsPage.enterSearch("10001");
        resultsPage.clickSearchButton();
        Assert.assertTrue(resultsPage.getStoreResultsCount() > 0, "No stores found");
        String address = resultsPage.getStoreAddress(0);
        Assert.assertNotNull(address, "Store address not found");
        Assert.assertFalse(address.isEmpty(), "Store address is empty");
    }

    @AfterMethod
    public void tearDown() {
        DriverFactory.quitDriver();
    }
}
