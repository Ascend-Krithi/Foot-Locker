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
 * Acceptance Criteria ID: 2167
 * Test Scenario ID: SCRUM-15408 TS-005
 * Test Case ID: TC-001
 * Description: Set '375 Washington Street, Boston, MA 02108' as preferred store
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
    public void testSetMyStore() {
        homePage.open(ConfigReader.get("baseUrl"));
        homePage.clickFindAStore();
        storeLocatorPage.clickSelectMyStore();
        storeResultsPage.enterLocation("Boston, MA");
        Assert.assertTrue(storeResultsPage.isSearchButtonEnabled(), "Search button should be enabled after entering location");
        storeResultsPage.clickSearch();
        Assert.assertTrue(storeResultsPage.isStoreWithAddressVisible("375 Washington Street, Boston, MA 02108"), "Store with address not visible");
        storeResultsPage.setMyStoreForAddress("375 Washington Street, Boston, MA 02108");
        // No assertion here as confirmation is in next test
    }

    @AfterMethod
    public void tearDown() {
        DriverFactory.quitDriver();
    }
}
