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
 * Acceptance Criteria ID: AC3
 * Test Scenario ID: SCRUM-15408 TS-003
 * Test Case ID: TC-001 (2015)
 * Description: Launch homepage, open Store Locator, search 'Boston, MA', and verify specific store address.
 * Expected: Store with address '375 Washington Street, Boston, MA 02108' is visible and address matches exactly.
 */
public class TS003_TC001_TestCase_SCRUM15408_TS003_TC001 {

    private WebDriver driver;

    @BeforeMethod
    public void setUp() {
        DriverFactory.initDriver();
        driver = DriverFactory.getDriver();
        driver.get(ConfigReader.get("base.url"));
    }

    @Test
    public void testVerifySpecificStoreAddress() {
        HomePage homePage = new HomePage(driver);
        homePage.clickFindAStore();

        StoreLocatorPage locatorPage = new StoreLocatorPage(driver);
        locatorPage.clickSelectMyStore();

        StoreResultsPage resultsPage = new StoreResultsPage(driver);
        resultsPage.enterSearchLocation("Boston, MA");
        resultsPage.clickSearchButton();

        String expectedAddress = "375 Washington Street, Boston, MA 02108";
        Assert.assertTrue(resultsPage.isStoreAddressPresent(expectedAddress), 
            "Store with address '" + expectedAddress + "' not found in results");
    }

    @AfterMethod
    public void tearDown() {
        DriverFactory.quitDriver();
    }
}