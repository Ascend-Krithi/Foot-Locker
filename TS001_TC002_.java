package com.fl.automation.tests;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.openqa.selenium.WebDriver;
import com.fl.automation.core.DriverFactory;
import com.fl.automation.pages.HomePage;
import com.fl.automation.pages.StoreLocatorPage;

/**
 * Acceptance Criteria ID: SCRUM-17166
 * Test Scenario ID: TS-001
 * Test Case ID: TC-002
 * Description: Launch app, Click Find a Store, Click Select My Store, Check for Location textbox and Search for Stores button
 */
public class TS001_TC002_ {
    private WebDriver driver;
    private HomePage homePage;
    private StoreLocatorPage locatorPage;

    @BeforeMethod
    public void setUp() {
        driver = DriverFactory.getDriver();
        homePage = new HomePage(driver);
        locatorPage = new StoreLocatorPage(driver);
    }

    @Test
    public void testLocationTextboxAndSearchButtonPresent() {
        homePage.launch();
        homePage.clickFindStore();
        locatorPage.clickSelectMyStore();
        Assert.assertTrue(locatorPage.isLocationTextboxPresent(), "Location textbox should be present");
        Assert.assertTrue(locatorPage.isSearchButtonPresent(), "Search for Stores button should be present");
    }

    @AfterMethod
    public void tearDown() {
        DriverFactory.quitDriver();
    }
}
