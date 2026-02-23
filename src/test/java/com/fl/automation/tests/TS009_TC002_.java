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
 * Acceptance Criteria ID: 2172
 * Test Scenario ID: SCRUM-15408 TS-009
 * Test Case ID: TC-002
 * Description: Leave location empty, click search if enabled, verify error message displayed
 */
public class TS009_TC002_ {
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
    public void testErrorMessageWhenLocationEmptyAndSearchClicked() {
        homePage.open(ConfigReader.get("baseUrl"));
        homePage.clickFindAStore();
        storeLocatorPage.clickSelectMyStore();
        Assert.assertTrue(storeResultsPage.isLocationInputEmpty(), "Location textbox should be empty");
        if (storeResultsPage.isSearchButtonEnabled()) {
            storeResultsPage.clickSearch();
            Assert.assertTrue(storeResultsPage.isErrorMessageDisplayed(), "Error message not displayed when searching with empty location");
        }
    }

    @AfterMethod
    public void tearDown() {
        DriverFactory.quitDriver();
    }
}
