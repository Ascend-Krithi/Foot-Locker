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
 * Test Scenario ID: TS-006
 * Test Case ID: TC-001
 * Description: Launch homepage -> Click Find a Store -> Click Select My Store -> Enter 'Boston, MA' and search -> Click 'Set My Store' for store at '375 Washington Street, Boston, MA 02108' -> Verify confirmation indicator -> Navigate to another section and verify selected store appears consistently
 */
public class TS006_TC001_ {
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
    public void testConfirmationIndicatorAndStoreConsistency() {
        homePage.open(ConfigReader.get("baseUrl"));
        homePage.clickFindAStore();
        storeLocatorPage.clickSelectMyStore();
        storeResultsPage.enterLocation("Boston, MA");
        storeResultsPage.clickSearchForStores();
        storeResultsPage.setMyStoreByAddress("375 Washington Street, Boston, MA 02108");
        Assert.assertTrue(storeResultsPage.isConfirmationIndicatorPresent("375 Washington Street, Boston, MA 02108"), "Confirmation indicator not present");
        // Navigate to another section (simulate by going to homepage and back)
        homePage.open(ConfigReader.get("baseUrl"));
        homePage.clickFindAStore();
        storeLocatorPage.clickSelectMyStore();
        Assert.assertTrue(storeResultsPage.isStoreSaved("375 Washington Street, Boston, MA 02108"), "Selected store does not appear consistently");
    }

    @AfterMethod
    public void tearDown() {
        DriverFactory.quitDriver();
    }
}
