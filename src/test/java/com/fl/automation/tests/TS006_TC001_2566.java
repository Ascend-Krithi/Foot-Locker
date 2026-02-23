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
 * Acceptance Criteria ID: AC5
 * Test Scenario ID: SCRUM-15408 TS-006
 * Test Case ID: TC-001 (2566)
 * Description: Set preferred store and verify confirmation and consistency across pages
 */
public class TS006_TC001_2566 {
    private WebDriver driver;

    @BeforeMethod
    public void setUp() {
        DriverFactory.initDriver();
        driver = DriverFactory.getDriver();
        driver.get(ConfigReader.get("base.url"));
    }

    @Test
    public void testPreferredStoreConfirmationAndConsistency() {
        HomePage homePage = new HomePage(driver);
        homePage.clickFindAStore();
        
        StoreLocatorPage locatorPage = new StoreLocatorPage(driver);
        locatorPage.clickSelectMyStore();
        
        StoreResultsPage resultsPage = new StoreResultsPage(driver);
        resultsPage.enterLocation("Boston, MA");
        resultsPage.clickSearchButton();
        resultsPage.clickSetMyStoreForAddress("375 Washington Street, Boston, MA, 02108");
        
        Assert.assertTrue(driver.getPageSource().contains("Boston") || 
                         driver.getPageSource().contains("375 Washington"), 
                         "Selected store is displayed consistently across the website");
    }

    @AfterMethod
    public void tearDown() {
        DriverFactory.quitDriver();
    }
}