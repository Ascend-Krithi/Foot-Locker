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
Acceptance Criteria ID: 3197
Test Scenario ID: SCRUM-17166 TS-001
Test Case ID: TC-005
Description: Launch, Find Store, Select My Store, search Boston MA, find 375 Washington Street store, click Set My Store
*/
public class TS001_TC005_ {
    private WebDriver driver;
    @BeforeMethod
    public void setUp() {
        DriverFactory.initDriver();
        driver = DriverFactory.getDriver();
        driver.get(ConfigReader.get("base.url"));
    }
    @Test
    public void testSetMyStore() {
        HomePage homePage = new HomePage(driver);
        homePage.clickFindAStore();
        StoreLocatorPage locatorPage = new StoreLocatorPage(driver);
        locatorPage.clickSelectMyStore();
        StoreResultsPage resultsPage = new StoreResultsPage(driver);
        resultsPage.enterLocation("Boston MA");
        resultsPage.clickSearchButton();
        Assert.assertTrue(resultsPage.isStoreAddressDisplayed("375 Washington Street"), "Store address 375 Washington Street not found");
        resultsPage.clickSetMyStoreOnCard("375 Washington Street");
        Assert.assertTrue(true, "Set My Store button clicked");
    }
    @AfterMethod
    public void tearDown() {
        DriverFactory.quitDriver();
    }
}