package com.fl.automation.tests;

import com.fl.automation.core.ConfigReader;
import com.fl.automation.core.DriverFactory;
import com.fl.automation.pages.HomePage;
import com.fl.automation.pages.StoreLocatorPage;
import com.fl.automation.pages.StoreResultsPage;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.*;

/**
 * Acceptance Criteria ID: SCRUM-15408
 * Test Scenario ID: TS-004
 * Test Case ID: TC-001
 * Description: Launch homepage -> Click Find a Store -> Click Select My Store -> Enter 'Boston, MA' and search -> Verify store with address '375 Washington Street, Boston, MA 02108' is present
 */
public class TS004_TC001_ {
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
    public void testStoreWithSpecificAddressPresent() {
        homePage.open(ConfigReader.get("baseUrl"));
        homePage.clickFindAStore();
        storeLocatorPage.clickSelectMyStore();
        storeResultsPage.enterLocation("Boston, MA");
        storeResultsPage.clickSearchForStores();
        Assert.assertTrue(storeResultsPage.isStoreWithAddressPresent("375 Washington Street, Boston, MA 02108"), "Store with address not found");
    }

    @AfterMethod
    public void tearDown() {
        DriverFactory.quitDriver();
    }
}
