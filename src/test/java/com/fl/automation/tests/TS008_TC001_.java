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
 * Acceptance Criteria ID: 1652
 * Test Scenario ID: SCRUM-15408 TS-008
 * Test Case ID: TC-001
 * Description: Test store selection button is present
 */
public class TS008_TC001_ {
    private WebDriver driver;

    @BeforeMethod
    public void setUp() {
        DriverFactory.initDriver();
        driver = DriverFactory.getDriver();
        driver.get(ConfigReader.get("base.url"));
    }

    @Test
    public void testSetMyStoreButtonPresent() {
        HomePage homePage = new HomePage(driver);
        homePage.clickFindAStore();
        StoreLocatorPage locatorPage = new StoreLocatorPage(driver);
        locatorPage.clickSelectMyStore();
        StoreResultsPage resultsPage = new StoreResultsPage(driver);
        resultsPage.enterSearch("10001");
        resultsPage.clickSearchButton();
        Assert.assertTrue(resultsPage.isSetMyStoreButtonPresent(0), "Set My Store button not present in first result");
    }

    @AfterMethod
    public void tearDown() {
        DriverFactory.quitDriver();
    }
}
