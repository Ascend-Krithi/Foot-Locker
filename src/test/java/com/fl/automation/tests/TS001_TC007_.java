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
Description: Launch homepage, click Find a Store, click Select My Store, enter Boston, MA, click Search for Stores, locate store 375 Washington Street, Boston, MA 02108, click Set My Store, navigate to another page, return to homepage, check if selected store persists as preferred store
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
    public void testPreferredStorePersists() {
        HomePage homePage = new HomePage(driver);
        homePage.clickFindAStore();
        StoreLocatorPage locatorPage = new StoreLocatorPage(driver);
        locatorPage.clickSelectMyStore();
        StoreResultsPage resultsPage = new StoreResultsPage(driver);
        resultsPage.enterLocation("Boston, MA");
        resultsPage.clickSearchButton();
        Assert.assertTrue(resultsPage.isStoreWithAddressPresent("375 Washington Street, Boston, MA 02108"), "Store with address not found");
        resultsPage.setMyStoreForAddress("375 Washington Street, Boston, MA 02108");
        resultsPage.navigateToAnotherPage();
        resultsPage.returnToHomePage();
        Assert.assertTrue(resultsPage.isSelectedStoreInHeader("375 Washington Street, Boston, MA 02108"), "Preferred store did not persist after navigation");
    }
    @AfterMethod
    public void tearDown() {
        DriverFactory.quitDriver();
    }
}
