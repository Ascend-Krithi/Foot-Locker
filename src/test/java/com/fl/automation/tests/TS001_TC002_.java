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

/**
 * Acceptance Criteria ID: SCRUM-17166
 * Test Scenario ID: TS-001
 * Test Case ID: TC-002
 * Description: Launch homepage, click 'Find a Store', click 'Select My Store', check for 'Location' textbox and 'Search for Stores' button
 */
public class TS001_TC002_ {
    private WebDriver driver;
    private HomePage homePage;
    private StoreLocatorPage storeLocatorPage;

    @BeforeMethod
    public void setUp() {
        driver = DriverFactory.getDriver();
        driver.get(ConfigReader.get("baseUrl"));
        homePage = new HomePage(driver);
        storeLocatorPage = new StoreLocatorPage(driver);
    }

    @Test
    public void testLocationTextboxAndSearchButtonPresent() {
        homePage.clickFindStore();
        Assert.assertTrue(storeLocatorPage.isSelectMyStoreVisible(), "Select My Store link not visible");
        storeLocatorPage.clickSelectMyStore();
        Assert.assertTrue(storeLocatorPage.isSearchInputPresent(), "Location textbox not present");
        Assert.assertTrue(storeLocatorPage.isSearchButtonPresent(), "Search for Stores button not present");
    }

    @AfterMethod
    public void tearDown() {
        DriverFactory.quitDriver();
    }
}
