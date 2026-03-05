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
 * Test Scenario ID: TS-001
 * Test Case ID: TC-005
 * Description: Launch homepage, click 'Find a Store', click 'Select My Store', enter 'Boston, MA', click 'Search for Stores', locate store '375 Washington Street, Boston, MA 02108', click 'Set My Store' for Boston location
 */
public class TS001_TC005_ {
    private WebDriver driver;
    private HomePage homePage;
    private StoreLocatorPage storeLocatorPage;
    private StoreResultsPage storeResultsPage;

    @BeforeMethod
    public void setUp() {
        driver = DriverFactory.getDriver();
        driver.get(ConfigReader.get("baseUrl"));
        homePage = new HomePage(driver);
        storeLocatorPage = new StoreLocatorPage(driver);
        storeResultsPage = new StoreResultsPage(driver);
    }

    @Test
    public void testSetMyStoreForBoston() {
        homePage.clickFindStore();
        storeLocatorPage.clickSelectMyStore();
        storeLocatorPage.enterLocation("Boston, MA");
        storeLocatorPage.clickSearchButton();
        Assert.assertTrue(storeResultsPage.isStorePresent("375 Washington Street, Boston, MA 02108"), "Store not found");
        storeResultsPage.clickSetMyStore("375 Washington Street, Boston, MA 02108");
        // No assertion for confirmation here, just action
    }

    @AfterMethod
    public void tearDown() {
        DriverFactory.quitDriver();
    }
}
