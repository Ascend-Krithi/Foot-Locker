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
 * Test Scenario ID: TS-005
 * Test Case ID: TC-001 (2437)
 * Description: Set '375 Washington Street, Boston, MA 02108' as preferred store
 */
public class TS005_TC001_2437 {
    private WebDriver driver;
    private HomePage homePage;
    private StoreLocatorPage storeLocatorPage;
    private StoreResultsPage storeResultsPage;

    @BeforeMethod
    public void setUp() {
        DriverFactory.initDriver();
        driver = DriverFactory.getDriver();
        homePage = new HomePage(driver);
        storeLocatorPage = new StoreLocatorPage(driver);
        storeResultsPage = new StoreResultsPage(driver);
    }

    @Test
    public void testSetPreferredStore() {
        homePage.open(ConfigReader.get("baseUrl"));
        homePage.clickFindAStore();
        storeLocatorPage.clickSelectMyStore();
        storeLocatorPage.enterLocation("Boston, MA");
        storeLocatorPage.clickSearchButton();
        storeResultsPage.setMyStoreByAddress("375 Washington Street, Boston, MA 02108");
        Assert.assertTrue(storeResultsPage.isConfirmationIndicatorDisplayed(), "Preferred store not set");
    }

    @AfterMethod
    public void tearDown() {
        DriverFactory.quitDriver();
    }
}
