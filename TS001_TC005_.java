package com.fl.automation.tests;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import com.fl.automation.core.DriverFactory;
import com.fl.automation.pages.HomePage;
import com.fl.automation.pages.StoreLocatorPage;
import com.fl.automation.pages.StoreResultsPage;

/**
 * Acceptance Criteria ID: SCRUM-17166
 * Test Scenario ID: TS-001
 * Test Case ID: TC-005
 * Description: Launch app, Click Find a Store, Click Select My Store, Enter Boston MA, Click Search, Locate 375 Washington Street store, Click Set My Store
 */
public class TS001_TC005_ {
    private WebDriver driver;
    private HomePage homePage;
    private StoreLocatorPage locatorPage;
    private StoreResultsPage resultsPage;

    @BeforeMethod
    public void setUp() {
        driver = DriverFactory.getDriver();
        homePage = new HomePage(driver);
        locatorPage = new StoreLocatorPage(driver);
        resultsPage = new StoreResultsPage(driver);
    }

    @Test
    public void testSetMyStore() {
        homePage.launch();
        homePage.clickFindStore();
        locatorPage.clickSelectMyStore();
        locatorPage.enterLocation("Boston MA");
        locatorPage.clickSearchButton();
        WebElement card = resultsPage.getStoreCardByAddress("375 Washington Street Boston MA 02108");
        Assert.assertNotNull(card, "Store card should be present");
        resultsPage.clickSetMyStoreOnCard(card);
    }

    @AfterMethod
    public void tearDown() {
        DriverFactory.quitDriver();
    }
}
