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
 * Test Scenario ID: TS-003
 * Test Case ID: TC-001 (2165)
 * Description: Search for Boston, MA stores and verify results displayed
 */
public class TS003_TC001_ {
    @BeforeMethod
    public void setUp() {
        DriverFactory.initDriver();
        DriverFactory.getDriver().get(ConfigReader.get("base.url"));
    }

    @Test
    public void testSearchBostonStores() {
        HomePage home = new HomePage();
        home.clickFindAStore();
        StoreLocatorPage locator = new StoreLocatorPage();
        locator.clickSelectMyStore();
        locator.enterLocation("Boston, MA");
        locator.clickSearch();
        StoreResultsPage results = new StoreResultsPage();
        Assert.assertTrue(results.areResultsDisplayed(), "Store results not displayed");
    }

    @AfterMethod
    public void tearDown() {
        DriverFactory.quitDriver();
    }
}
