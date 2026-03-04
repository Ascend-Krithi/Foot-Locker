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
 * Description: Launch homepage, click Find a Store, click Select My Store, verify Location textbox and Search for Stores button
 */
public class TS001_TC002_ {
    private WebDriver driver;
    private HomePage homePage;
    private StoreLocatorPage storeLocatorPage;

    @BeforeMethod
    public void setUp() {
        driver = DriverFactory.getDriver();
        homePage = new HomePage(driver);
        storeLocatorPage = new StoreLocatorPage(driver);
    }

    @Test
    public void testSelectMyStoreDisplaysLocationTextboxAndSearchButton() {
        homePage.launchHomePage(ConfigReader.getProperty("baseUrl"));
        homePage.clickFindStore();
        storeLocatorPage.clickSelectMyStore();
        Assert.assertTrue(storeLocatorPage.isLocationTextboxDisplayed(), "Location textbox not displayed");
        Assert.assertTrue(storeLocatorPage.isSearchButtonDisplayed(), "Search for Stores button not displayed");
    }

    @AfterMethod
    public void tearDown() {
        DriverFactory.quitDriver();
    }
}
