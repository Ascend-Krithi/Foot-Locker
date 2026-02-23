package tests;

import core.DriverFactory;
import core.ConfigReader;
import org.testng.annotations.*;
import pages.HomePage;
import pages.StoreLocatorPage;
import pages.StoreResultsPage;
import org.testng.Assert;

/**
 * Acceptance Criteria ID: SCRUM-15408
 * Test Scenario ID: TS-006
 * Test Case ID: TC-001 (2168)
 * Description: Set Boston store and verify confirmation indicator displayed
 */
public class TS006_TC001_ {
    @BeforeMethod
    public void setUp() {
        DriverFactory.initDriver();
        DriverFactory.getDriver().get(ConfigReader.get("base.url"));
    }

    @Test
    public void testSetBostonStoreConfirmation() {
        HomePage home = new HomePage();
        home.clickFindAStore();
        StoreLocatorPage locator = new StoreLocatorPage();
        locator.clickSelectMyStore();
        locator.enterLocation("Boston, MA");
        locator.clickSearch();
        StoreResultsPage results = new StoreResultsPage();
        results.setMyStore("375 Washington Street, Boston, MA 02108");
        Assert.assertTrue(results.isConfirmationIndicatorDisplayed(), "Confirmation indicator not displayed");
    }

    @AfterMethod
    public void tearDown() {
        DriverFactory.quitDriver();
    }
}
