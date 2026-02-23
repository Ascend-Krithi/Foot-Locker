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
 * Acceptance Criteria ID: 2165
 * Test Scenario ID: SCRUM-15408 TS-003
 * Test Case ID: TC-001
 * Description: Enter 'Boston, MA' and verify relevant store results displayed
 */
public class TS003_TC001_ {
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
    public void testSearchBostonStores() {
        homePage.open(ConfigReader.get("baseUrl"));
        homePage.clickFindAStore();
        storeLocatorPage.clickSelectMyStore();
        storeResultsPage.enterLocation("Boston, MA");
        Assert.assertTrue(storeResultsPage.isSearchButtonEnabled(), "Search button should be enabled after entering location");
        storeResultsPage.clickSearch();
        Assert.assertTrue(storeResultsPage.getStoreCards().size() > 0, "No store results displayed for Boston, MA");
    }

    @AfterMethod
    public void tearDown() {
        DriverFactory.quitDriver();
    }
}
