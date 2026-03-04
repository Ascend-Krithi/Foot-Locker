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

/**
 * Acceptance Criteria ID: SCRUM-17166
 * Test Scenario ID: TS-002
 * Test Case ID: TC-002
 * Description: Launch homepage, click Find a Store, click Select My Store, enter 'InvalidLocation123', click Search for Stores, verify no results message
 */
public class TS002_TC002_ {
    private WebDriver driver;
    private HomePage homePage;
    private StoreLocatorPage storeLocatorPage;
    private StoreResultsPage storeResultsPage;

    @BeforeMethod
    public void setUp() {
        driver = DriverFactory.getDriver();
        homePage = new HomePage(driver);
        storeLocatorPage = new StoreLocatorPage(driver);
        storeResultsPage = new StoreResultsPage(driver);
    }

    @Test
    public void testInvalidLocationShowsNoResultsMessage() {
        homePage.launchHomePage(ConfigReader.getProperty("baseUrl"));
        homePage.clickFindStore();
        storeLocatorPage.clickSelectMyStore();
        storeLocatorPage.enterLocation("InvalidLocation123");
        storeLocatorPage.clickSearchForStores();
        Assert.assertTrue(storeResultsPage.isNoResultsMessageDisplayed(), "No results message not displayed");
    }

    @AfterMethod
    public void tearDown() {
        DriverFactory.quitDriver();
    }
}
