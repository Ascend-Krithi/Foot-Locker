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
Acceptance Criteria ID: 2437
Test Scenario ID: SCRUM-15408 TS-005 TC-001
Test Case ID: 2437
Description: Set '375 Washington Street, Boston, MA 02108' as My Store and verify store is saved
*/
public class TS005_TC001_2437 {
    private WebDriver driver;

    @BeforeMethod
    public void setUp() {
        DriverFactory.initDriver("chrome");
        driver = DriverFactory.getDriver();
        driver.get(ConfigReader.get("base.url"));
    }

    @Test
    public void testSetMyStoreAndVerifySaved() {
        HomePage homePage = new HomePage(driver);
        homePage.clickFindAStore();
        homePage.clickSelectMyStore();
        StoreLocatorPage locatorPage = new StoreLocatorPage(driver);
        locatorPage.enterLocation("Boston, MA");
        locatorPage.clickSearchForStores();
        StoreResultsPage resultsPage = new StoreResultsPage(driver);
        resultsPage.setMyStore("375 Washington Street, Boston, MA 02108");
        Assert.assertTrue(resultsPage.isStoreSaved("375 Washington Street, Boston, MA 02108"), "Store was not saved as My Store");
    }

    @AfterMethod
    public void tearDown() {
        DriverFactory.quitDriver();
    }
}
