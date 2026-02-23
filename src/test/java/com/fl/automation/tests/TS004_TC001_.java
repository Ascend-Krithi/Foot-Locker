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
 * Acceptance Criteria ID: TC 2436
 * Test Scenario ID: SCRUM-15408 TS-004
 * Test Case ID: TC-001
 * Description: Launch homepage, Click 'Find a Store' then 'Select My Store', Enter 'Boston, MA' and click 'Search for Stores', Verify store with address '375 Washington Street, Boston, MA 02108' is present in results
 */
public class TS004_TC001_ {
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
    public void testStoreAddressPresent() {
        homePage.clickFindAStore();
        locatorPage.clickSelectMyStore();
        locatorPage.enterLocation("Boston, MA");
        locatorPage.clickSearchForStores();
        Assert.assertTrue(resultsPage.isStoreWithAddressPresent("375 Washington Street, Boston, MA 02108"), "Store address not found in results");
    }

    @AfterMethod
    public void tearDown() {
        DriverFactory.quitDriver();
    }
}
