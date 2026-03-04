package com.fl.automation.tests;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.openqa.selenium.WebDriver;
import com.fl.automation.core.DriverFactory;
import com.fl.automation.core.ConfigReader;
import com.fl.automation.pages.HomePage;
import com.fl.automation.pages.StoreLocatorPage;
import com.fl.automation.pages.StoreResultsPage;

/**
 * Acceptance Criteria ID: SCRUM-17166
 * Test Scenario ID: TS-005
 * Test Case ID: TC-001
 * Description: Launch homepage, click Find a Store, click Select My Store, enter 'Boston, MA', click Search for Stores, locate store '375 Washington Street, Boston, MA 02108', click Set My Store, verify confirmation and persistence across pages
 */
public class TS005_TC001_ {
    private WebDriver driver;
    private HomePage homePage;
    private StoreLocatorPage storeLocatorPage;
    private StoreResultsPage storeResultsPage;

    @BeforeMethod
    public void setUp() {
        driver = DriverFactory.getDriver();
        homePage = new HomePage(driver);
        storeLocatorPage = new StoreLocatorPage(driver);
        storeResultsPage = new StoreResultsPage(driver);
    }

    @Test
    public void testSetMyStoreConfirmationAndPersistence() {
        homePage.launchHomePage(ConfigReader.getProperty("baseUrl"));
        homePage.clickFindStore();
        storeLocatorPage.clickSelectMyStore();
        storeLocatorPage.enterLocation("Boston, MA");
        storeLocatorPage.clickSearchForStores();
        Assert.assertTrue(storeResultsPage.isStoreAddressPresent("375 Washington Street, Boston, MA 02108"), "Store address not present");
        storeResultsPage.setMyStore("375 Washington Street, Boston, MA 02108");
        Assert.assertTrue(storeResultsPage.isStoreSetConfirmationDisplayed("375 Washington Street, Boston, MA 02108"), "Store set confirmation not displayed");
        homePage.launchHomePage(ConfigReader.getProperty("baseUrl"));
        homePage.clickFindStore();
        storeLocatorPage.clickSelectMyStore();
        storeLocatorPage.enterLocation("Boston, MA");
        storeLocatorPage.clickSearchForStores();
        Assert.assertTrue(storeResultsPage.isStoreSetConfirmationDisplayed("375 Washington Street, Boston, MA 02108"), "Store persistence not confirmed");
    }

    @AfterMethod
    public void tearDown() {
        DriverFactory.quitDriver();
    }
}
