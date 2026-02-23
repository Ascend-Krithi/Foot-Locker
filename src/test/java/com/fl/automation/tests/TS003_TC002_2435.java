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
 * Acceptance Criteria ID: SCRUM-15408
 * Test Scenario ID: TS-003
 * Test Case ID: TC-002 (2435)
 * Description: Search with invalid location
 */
public class TS003_TC002_2435 {
    private WebDriver driver;
    private HomePage homePage;
    private StoreLocatorPage storeLocatorPage;
    private StoreResultsPage storeResultsPage;

    @BeforeMethod
    public void setUp() {
        DriverFactory.initDriver();
        driver = DriverFactory.getDriver();
        driver.get(ConfigReader.get("baseUrl"));
        homePage = new HomePage(driver);
        storeLocatorPage = new StoreLocatorPage(driver);
        storeResultsPage = new StoreResultsPage(driver);
    }

    @Test
    public void searchWithInvalidLocation() {
        homePage.clickFindStore();
        storeLocatorPage.enterLocation("InvalidLocation123");
        storeLocatorPage.clickSearch();
        Assert.assertTrue(storeResultsPage.isEmptyResultsMessageDisplayed(), "No locations message should be displayed for invalid location");
    }

    @AfterMethod
    public void tearDown() {
        DriverFactory.quitDriver();
    }
}
