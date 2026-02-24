package com.fl.automation.tests;

import com.fl.automation.core.ConfigReader;
import com.fl.automation.core.DriverFactory;
import com.fl.automation.pages.HomePage;
import com.fl.automation.pages.StoreLocatorPage;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
/**
Acceptance Criteria ID: 2700
Test Scenario ID: SCRUM-15408 TS-001
Test Case ID: TC-004
Description: Launch website, click 'Find a Store', click 'Select My Store', verify 'Find a Store' popup opens with Location textbox and Search for Stores button
*/
public class TS001_TC004_ {
    private WebDriver driver;
    @BeforeMethod
    public void setUp() {
        DriverFactory.initDriver();
        driver = DriverFactory.getDriver();
        driver.get(ConfigReader.get("base.url"));
    }
    @Test
    public void testFindStorePopupWithLocationInput() {
        HomePage homePage = new HomePage();
        homePage.clickFindStore();
        StoreLocatorPage locatorPage = new StoreLocatorPage();
        locatorPage.clickSelectMyStore();
        Assert.assertTrue(locatorPage.isLocationInputVisible(), "Location textbox should be visible in popup");
    }
    @AfterMethod
    public void tearDown() {
        DriverFactory.quitDriver();
    }
}
