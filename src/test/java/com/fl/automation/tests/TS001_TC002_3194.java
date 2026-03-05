// Acceptance Criteria ID: SCRUM-17166
// Test Scenario ID: TS-001
// Test Case ID: TC-002
// Description: Verify search input is present and functional on Store Locator page
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

public class TS001_TC002_3194 {
    private WebDriver driver;
    private StoreLocatorPage storeLocatorPage;

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
    public void verifySearchInputPresentAndFunctional() {
        Assert.assertTrue(storeLocatorPage.getSearchInput().isDisplayed(), "Search input should be present");
        storeLocatorPage.searchStore("New York");
        Assert.assertFalse(storeLocatorPage.isEmptyResultsMessageDisplayed(), "Results should be shown for valid search");
    }
}
