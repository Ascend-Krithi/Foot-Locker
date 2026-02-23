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
 * Acceptance Criteria ID: 2170
 * Test Scenario ID: SCRUM-15408 TS-008
 * Test Case ID: TC-001
 * Description: Enter 'Atlantis', verify 'No stores found' message displayed
 */
public class TS008_TC001_ {
    private WebDriver driver;
    private HomePage homePage;
    private StoreLocatorPage storeLocatorPage;
    private StoreResultsPage storeResultsPage;

    @BeforeMethod
    public void setUp() {
        driver = DriverFactory.getDriver();
        homePage = new HomePage(driver);
        storeLocatorPage = new StoreLocatorPage(driver);
        storeResultsPage = new StoreResultsPage(driver);
    }

    @Test
    public void testNoStoresFoundMessage() {
        homePage.open(ConfigReader.get("baseUrl"));
        homePage.clickFindAStore();
        storeLocatorPage.clickSelectMyStore();
        storeResultsPage.enterLocation("Atlantis");
        Assert.assertTrue(storeResultsPage.isSearchButtonEnabled(), "Search button should be enabled after entering location");
        storeResultsPage.clickSearch();
        Assert.assertTrue(storeResultsPage.isNoStoresMessageDisplayed(), "No stores found message not displayed");
    }

    @AfterMethod
    public void tearDown() {
        DriverFactory.quitDriver();
    }
}
