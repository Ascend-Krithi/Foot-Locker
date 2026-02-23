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
 * Acceptance Criteria ID: 2164
 * Test Scenario ID: SCRUM-15408 TS-002
 * Test Case ID: TC-001
 * Description: Click 'Find a Store', then 'Select My Store', verify location textbox and search button
 */
public class TS002_TC001_ {
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
    public void testSelectMyStoreOpensPopup() {
        homePage.open(ConfigReader.get("baseUrl"));
        homePage.clickFindAStore();
        storeLocatorPage.clickSelectMyStore();
        Assert.assertTrue(storeResultsPage.isLocationInputEmpty(), "Location textbox not present or not empty");
        Assert.assertTrue(storeResultsPage.isSearchButtonEnabled() == false, "Search button should be disabled when location is empty");
    }

    @AfterMethod
    public void tearDown() {
        DriverFactory.quitDriver();
    }
}
