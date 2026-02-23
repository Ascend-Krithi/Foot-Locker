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
 * Acceptance Criteria ID: TC 2434
 * Test Scenario ID: SCRUM-15408 TS-003
 * Test Case ID: TC-001
 * Description: Launch homepage, Click 'Find a Store' then 'Select My Store', Enter 'Boston, MA' in Location textbox, Click 'Search for Stores' button, Verify store results in or near Boston are displayed
 */
public class TS003_TC001_ {
    private WebDriver driver;
    private HomePage homePage;
    private StoreLocatorPage locatorPage;
    private StoreResultsPage resultsPage;

    @BeforeMethod
    public void setUp() {
        DriverFactory.initDriver();
        driver = DriverFactory.getDriver();
        driver.get(ConfigReader.get("baseUrl"));
        homePage = new HomePage();
        locatorPage = new StoreLocatorPage();
        resultsPage = new StoreResultsPage();
    }

    @Test
    public void testStoreResultsForBoston() {
        homePage.clickFindAStore();
        locatorPage.clickSelectMyStore();
        locatorPage.enterLocation("Boston, MA");
        locatorPage.clickSearchForStores();
        Assert.assertTrue(resultsPage.isStoreResultsDisplayed(), "No store results displayed for Boston, MA");
    }

    @AfterMethod
    public void tearDown() {
        DriverFactory.quitDriver();
    }
}
