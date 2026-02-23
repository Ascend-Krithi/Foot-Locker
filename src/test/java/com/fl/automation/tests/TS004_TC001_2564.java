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
 * Test Scenario ID: SCRUM-15408 TS-004
 * Test Case ID: TC-001 (2564)
 * Description: Search Boston MA and verify store address matches exactly
 */
public class TS004_TC001_2564 {
    private WebDriver driver;

    @BeforeMethod
    public void setUp() {
        DriverFactory.initDriver();
        driver = DriverFactory.getDriver();
        driver.get(ConfigReader.get("base.url"));
    }

    @Test
    public void testStoreAddressMatchesExactly() {
        HomePage homePage = new HomePage(driver);
        homePage.clickFindAStore();
        
        StoreLocatorPage locatorPage = new StoreLocatorPage(driver);
        locatorPage.clickSelectMyStore();
        
        StoreResultsPage resultsPage = new StoreResultsPage(driver);
        resultsPage.enterLocation("Boston, MA");
        resultsPage.clickSearchButton();
        
        Assert.assertTrue(resultsPage.isStoreAddressVisible("375 Washington Street, Boston, MA, 02108"), 
                         "The address matches exactly: 375 Washington Street, Boston, MA, 02108");
    }

    @AfterMethod
    public void tearDown() {
        DriverFactory.quitDriver();
    }
}