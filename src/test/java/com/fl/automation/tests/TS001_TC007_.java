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
Acceptance Criteria ID: 3199
Test Scenario ID: SCRUM-17166 TS-001
Test Case ID: TC-007
Description: Same as TC-006 plus navigate to another page, return to homepage, verify store persists
*/
public class TS001_TC007_ {
    private WebDriver driver;
    @BeforeMethod
    public void setUp() {
        DriverFactory.initDriver();
        driver = DriverFactory.getDriver();
        driver.get(ConfigReader.get("base.url"));
    }
    @Test
    public void testStorePersistence() {
        HomePage homePage = new HomePage(driver);
        homePage.clickFindAStore();
        StoreLocatorPage locatorPage = new StoreLocatorPage(driver);
        locatorPage.clickSelectMyStore();
        StoreResultsPage resultsPage = new StoreResultsPage(driver);
        resultsPage.enterLocation("Boston MA");
        resultsPage.clickSearchButton();
        Assert.assertTrue(resultsPage.isStoreAddressDisplayed("375 Washington Street"), "Store address 375 Washington Street not found");
        resultsPage.clickSetMyStoreOnCard("375 Washington Street");
        Assert.assertTrue(resultsPage.isConfirmationIndicatorDisplayed("375 Washington Street"), "Confirmation indicator not displayed");
        Assert.assertTrue(resultsPage.isStoreInHeader("375 Washington Street"), "Store not shown in header");
        resultsPage.navigateToAnotherPage();
        resultsPage.returnToHomePage();
        Assert.assertTrue(resultsPage.isStorePersistedInHeader("375 Washington Street"), "Store not persisted in header after navigation");
    }
    @AfterMethod
    public void tearDown() {
        DriverFactory.quitDriver();
    }
}