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
Acceptance Criteria ID: 3198
Test Scenario ID: SCRUM-17166 TS-001
Test Case ID: TC-006
Description: Launch homepage, click Find a Store, click Select My Store, enter Boston, MA, click Search for Stores, locate store 375 Washington Street, Boston, MA 02108, click Set My Store, check for confirmation indicator and verify selected store appears in header/location indicator
*/
public class TS001_TC006_ {
    private WebDriver driver;
    @BeforeMethod
    public void setUp() {
        DriverFactory.initDriver();
        driver = DriverFactory.getDriver();
        driver.get(ConfigReader.get("base.url"));
    }
    @Test
    public void testSetMyStoreConfirmation() {
        HomePage homePage = new HomePage(driver);
        homePage.clickFindAStore();
        StoreLocatorPage locatorPage = new StoreLocatorPage(driver);
        locatorPage.clickSelectMyStore();
        StoreResultsPage resultsPage = new StoreResultsPage(driver);
        resultsPage.enterLocation("Boston, MA");
        resultsPage.clickSearchButton();
        Assert.assertTrue(resultsPage.isStoreWithAddressPresent("375 Washington Street, Boston, MA 02108"), "Store with address not found");
        resultsPage.setMyStoreForAddress("375 Washington Street, Boston, MA 02108");
        Assert.assertTrue(resultsPage.isConfirmationIndicatorPresent(), "Confirmation indicator not present after setting store");
        Assert.assertTrue(resultsPage.isSelectedStoreInHeader("375 Washington Street, Boston, MA 02108"), "Selected store not shown in header");
    }
    @AfterMethod
    public void tearDown() {
        DriverFactory.quitDriver();
    }
}
