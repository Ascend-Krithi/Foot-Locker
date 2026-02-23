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
 * Test Case ID: TC-001
 * Description: Launch homepage -> Click Find a Store -> Click Select My Store -> Enter 'Boston, MA' and search -> Click 'Set My Store' for store at '375 Washington Street, Boston, MA 02108' -> Verify store is saved
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
    public void testSetMyStoreSavesStore() {
        homePage.open(ConfigReader.get("baseUrl"));
        homePage.clickFindAStore();
        storeLocatorPage.clickSelectMyStore();
        storeResultsPage.enterLocation("Boston, MA");
        storeResultsPage.clickSearchForStores();
        storeResultsPage.setMyStoreByAddress("375 Washington Street, Boston, MA 02108");
        Assert.assertTrue(storeResultsPage.isStoreSaved("375 Washington Street, Boston, MA 02108"), "Store is not saved as My Store");
    }

    @AfterMethod
    public void tearDown() {
        DriverFactory.quitDriver();
    }
}
