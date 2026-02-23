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
 * Acceptance Criteria ID: SCRUM-15408
 * Test Scenario ID: TS-002
 * Test Case ID: TC-001 (2433)
 * Description: Find a Store popup with Location textbox and Search button
 */
public class TS002_TC001_2433 {
    private WebDriver driver;
    private HomePage homePage;
    private StoreLocatorPage storeLocatorPage;

    @BeforeMethod
    public void setUp() {
        DriverFactory.initDriver();
        driver = DriverFactory.getDriver();
        driver.get(ConfigReader.get("baseUrl"));
        homePage = new HomePage(driver);
        storeLocatorPage = new StoreLocatorPage(driver);
    }

    @Test
    public void verifyFindStorePopupElements() {
        homePage.clickFindStore();
        Assert.assertTrue(storeLocatorPage.isSearchInputVisible(), "Location textbox should be visible in Find a Store popup");
    }

    @AfterMethod
    public void tearDown() {
        DriverFactory.quitDriver();
    }
}
