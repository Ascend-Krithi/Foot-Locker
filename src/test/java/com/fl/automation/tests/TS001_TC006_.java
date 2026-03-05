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
 * Acceptance Criteria ID: 
 * Test Scenario ID: SCRUM-17166 TS-001
 * Test Case ID: TC-006
 * Description: Launch homepage, navigate to Store Locator, search Boston MA, set store and verify confirmation indicator is displayed
 */
public class TS001_TC006_ {

    private WebDriver driver;

    @BeforeMethod
    public void setUp() {
        DriverFactory.initDriver();
        driver = DriverFactory.getDriver();
        driver.get(ConfigReader.get("base.url"));
    }

    @Test
    public void testConfirmationIndicatorAfterSetMyStore() {
        HomePage homePage = new HomePage(driver);
        homePage.clickFindAStore();

        StoreLocatorPage locatorPage = new StoreLocatorPage(driver);
        locatorPage.clickSelectMyStore();

        StoreResultsPage resultsPage = new StoreResultsPage(driver);
        resultsPage.enterLocation("Boston, MA");
        resultsPage.clickSearchButton();

        Assert.assertTrue(resultsPage.isStoreAddressVisible("375 Washington Street, Boston, MA 02108"), 
                "Store address not found");

        resultsPage.clickSetMyStoreForAddress("375 Washington Street, Boston, MA 02108");

        Assert.assertTrue(resultsPage.isConfirmationDisplayed(), "Confirmation indicator is not displayed");
    }

    @AfterMethod
    public void tearDown() {
        DriverFactory.quitDriver();
    }
}