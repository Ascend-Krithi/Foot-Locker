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
 * Acceptance Criteria ID: AC6
 * Test Scenario ID: SCRUM-15408 TS-006
 * Test Case ID: TC-001 (2018)
 * Description: Launch homepage, open Store Locator, search 'Boston, MA', set store, navigate to another page.
 * Expected: The selected store remains set and visible across pages.
 */
public class TS006_TC001_TestCase_SCRUM15408_TS006_TC001 {

    private WebDriver driver;

    @BeforeMethod
    public void setUp() {
        DriverFactory.initDriver();
        driver = DriverFactory.getDriver();
        driver.get(ConfigReader.get("base.url"));
    }

    @Test
    public void testStorePersistsAcrossPages() {
        HomePage homePage = new HomePage(driver);
        homePage.clickFindAStore();

        StoreLocatorPage locatorPage = new StoreLocatorPage(driver);
        locatorPage.clickSelectMyStore();

        StoreResultsPage resultsPage = new StoreResultsPage(driver);
        resultsPage.enterSearchLocation("Boston, MA");
        resultsPage.clickSearchButton();

        String targetAddress = "375 Washington Street, Boston, MA 02108";
        Assert.assertTrue(resultsPage.isStoreAddressPresent(targetAddress), 
            "Store with address '" + targetAddress + "' not found");
        
        resultsPage.clickSetMyStoreForAddress(targetAddress);
        
        driver.get("https://www.footlocker.com/men/shoes");
        
        Assert.assertTrue(resultsPage.isStoreVisibleInHeader("Boston") || resultsPage.isConfirmationDisplayed(), 
            "Selected store not visible after navigating to another page");
    }

    @AfterMethod
    public void tearDown() {
        DriverFactory.quitDriver();
    }
}
