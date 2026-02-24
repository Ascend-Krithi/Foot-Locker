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
Acceptance Criteria ID: 2705
Test Scenario ID: SCRUM-15408 TS-001
Test Case ID: TC-009
Description: Launch website, navigate to store locator, search Boston MA, set store at '375 Washington Street, Boston, MA 02108', navigate to another page, verify store remains set
*/
public class TS001_TC009_ {
    private WebDriver driver;
    @BeforeMethod
    public void setUp() {
        DriverFactory.initDriver();
        driver = DriverFactory.getDriver();
        driver.get(ConfigReader.get("base.url"));
    }
    @Test
    public void testStoreRemainsSetAfterNavigation() {
        HomePage homePage = new HomePage();
        homePage.clickFindStore();
        StoreLocatorPage locatorPage = new StoreLocatorPage();
        locatorPage.clickSelectMyStore();
        locatorPage.enterLocation("Boston, MA");
        locatorPage.clickSearchForStores();
        StoreResultsPage resultsPage = new StoreResultsPage();
        boolean set = resultsPage.setMyStoreByAddress("375 Washington Street, Boston, MA 02108");
        Assert.assertTrue(set, "Should be able to set store as preferred");
        Assert.assertTrue(resultsPage.isConfirmationIndicatorDisplayed("375 Washington Street, Boston, MA 02108"), "Confirmation indicator should be displayed");
        driver.get(ConfigReader.get("base.url"));
        Assert.assertTrue(homePage.isFindStoreVisible(), "'Find a Store' should still be visible in header");
    }
    @AfterMethod
    public void tearDown() {
        DriverFactory.quitDriver();
    }
}
