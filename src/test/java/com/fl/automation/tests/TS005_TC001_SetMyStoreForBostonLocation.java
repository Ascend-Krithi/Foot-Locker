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
Acceptance Criteria ID: AC4
Test Scenario ID: SCRUM-15408 TS-005
Test Case ID: TC-001
Description: Locate store with address 375 Washington Street, Boston, MA 02108 and click Set My Store
*/
public class TS005_TC001_SetMyStoreForBostonLocation {

    private WebDriver driver;

    @BeforeMethod
    public void setUp() {
        DriverFactory.initDriver();
        driver = DriverFactory.getDriver();
        driver.get(ConfigReader.get("base.url"));
    }

    @Test
    public void testSetMyStoreForBostonLocation() {
        HomePage homePage = new HomePage(driver);
        homePage.clickFindAStore();
        
        StoreLocatorPage locatorPage = new StoreLocatorPage(driver);
        locatorPage.clickSelectMyStore();
        
        StoreResultsPage resultsPage = new StoreResultsPage(driver);
        resultsPage.enterSearchLocation("Boston, MA");
        resultsPage.clickSearchButton();
        
        Assert.assertTrue(resultsPage.isStoreAddressPresent("375 Washington Street"), "Store at 375 Washington Street not found");
        resultsPage.clickSetMyStoreForAddress("375 Washington Street");
    }

    @AfterMethod
    public void tearDown() {
        DriverFactory.quitDriver();
    }
}