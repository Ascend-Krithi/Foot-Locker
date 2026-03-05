// Acceptance Criteria ID: SCRUM-17166
// Test Scenario ID: TS-004
// Test Case ID: TC-001
// Description: Verify search input accepts partial address and returns results
package com.fl.automation.tests;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.openqa.selenium.WebDriver;
import com.fl.automation.core.DriverFactory;
import com.fl.automation.core.ConfigReader;
import com.fl.automation.pages.HomePage;
import com.fl.automation.pages.StoreLocatorPage;
import com.fl.automation.pages.StoreResultsPage;

public class TS004_TC001_3230 {
    private WebDriver driver;
    private StoreLocatorPage storeLocatorPage;
    private StoreResultsPage storeResultsPage;

    @BeforeMethod
    public void setUp() {
        driver = DriverFactory.getDriver();
        driver.get(ConfigReader.getProperty("baseUrl"));
        new HomePage(driver).clickFindStoreHeader();
        storeLocatorPage = new StoreLocatorPage(driver);
    }

    @AfterMethod
    public void tearDown() {
        DriverFactory.quitDriver();
    }

    @Test
    public void verifySearchByPartialAddressReturnsResults() {
        storeLocatorPage.searchStore("Broadway");
        storeResultsPage = new StoreResultsPage(driver);
        Assert.assertTrue(storeResultsPage.getStoreCards().size() > 0, "Store cards should be displayed for partial address");
    }
}
