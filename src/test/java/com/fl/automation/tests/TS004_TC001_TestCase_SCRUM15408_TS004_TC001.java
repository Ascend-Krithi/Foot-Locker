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
 * Acceptance Criteria ID: 2166
 * Test Scenario ID: SCRUM-15408 TS-004
 * Test Case ID: TC-001
 * Description: Launch homepage -> Click Find a Store -> Click Select My Store -> Enter Boston, MA -> Click Search for Stores -> Review search results for store address 375 Washington Street, Boston, MA 02108
 */
public class TS004_TC001_TestCase_SCRUM15408_TS004_TC001 {
    private WebDriver driver;
    private HomePage homePage;
    private StoreLocatorPage storeLocatorPage;
    private StoreResultsPage storeResultsPage;

    @BeforeMethod
    public void setUp() {
        DriverFactory.initDriver();
        driver = DriverFactory.getDriver();
        driver.get(ConfigReader.get("base.url"));
        homePage = new HomePage(driver);
        storeLocatorPage = new StoreLocatorPage(driver);
        storeResultsPage = new StoreResultsPage(driver);
    }

    @Test
    public void testStoreAddressPresentInResults() {
        homePage.clickFindAStore();
        storeLocatorPage.clickSelectMyStore();
        storeLocatorPage.enterLocation("Boston, MA");
        storeLocatorPage.clickSearchForStores();
        Assert.assertTrue(storeResultsPage.isStoreAddressPresent("375 Washington Street, Boston, MA 02108"), "Store address not found in results");
    }

    @AfterMethod
    public void tearDown() {
        DriverFactory.quitDriver();
    }
}
