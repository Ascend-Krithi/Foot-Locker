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

/**
 * Acceptance Criteria ID: AC2
 * Test Scenario ID: SCRUM-17166 TS-001
 * Test Case ID: 3194
 * Description: Verify homepage loads, Find a Store popup appears, Select My Store opens store locator, and location input & search button are present.
 */
public class TS001_TC002_3194 {
    private WebDriver driver;
    private HomePage homePage;
    private StoreLocatorPage storeLocatorPage;

    @BeforeMethod
    public void setUp() {
        driver = DriverFactory.getDriver();
        driver.get(ConfigReader.getProperty("baseUrl"));
        homePage = new HomePage(driver);
        storeLocatorPage = new StoreLocatorPage(driver);
    }

    @Test
    public void testStoreLocatorPopupElements() {
        Assert.assertTrue(homePage.isHomePageLoaded(), "Homepage did not load.");
        homePage.clickFindStore();
        Assert.assertTrue(storeLocatorPage.isPopupDisplayed(), "Find a Store popup did not appear.");
        storeLocatorPage.clickSelectMyStore();
        Assert.assertTrue(storeLocatorPage.isLocationInputVisible(), "Location textbox is not present.");
        Assert.assertTrue(storeLocatorPage.isSearchButtonVisible(), "Search for Stores button is not present.");
    }

    @AfterMethod
    public void tearDown() {
        DriverFactory.quitDriver();
    }
}
