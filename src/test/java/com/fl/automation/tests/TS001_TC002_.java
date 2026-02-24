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
Acceptance Criteria ID: 2698
Test Scenario ID: SCRUM-15408 TS-001
Test Case ID: TC-002
Description: Launch website, click 'Find a Store', verify popup appears with message 'Choose a preferred store to make shopping easier'
*/
public class TS001_TC002_ {
    private WebDriver driver;
    @BeforeMethod
    public void setUp() {
        DriverFactory.initDriver();
        driver = DriverFactory.getDriver();
        driver.get(ConfigReader.get("base.url"));
    }
    @Test
    public void testFindStorePopupMessage() {
        HomePage homePage = new HomePage();
        homePage.clickFindStore();
        StoreLocatorPage locatorPage = new StoreLocatorPage();
        Assert.assertTrue(locatorPage.isPopupMessageDisplayed(), "Popup with message should appear");
    }
    @AfterMethod
    public void tearDown() {
        DriverFactory.quitDriver();
    }
}
