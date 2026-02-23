package tests;

import core.DriverFactory;
import core.ConfigReader;
import org.testng.annotations.*;
import pages.HomePage;
import pages.StoreLocatorPage;
import org.testng.Assert;

/**
 * Acceptance Criteria ID: SCRUM-15408
 * Test Scenario ID: TS-009
 * Test Case ID: TC-001 (2171)
 * Description: Leave location empty and verify Search button is disabled
 */
public class TS009_TC001_ {
    @BeforeMethod
    public void setUp() {
        DriverFactory.initDriver();
        DriverFactory.getDriver().get(ConfigReader.get("base.url"));
    }

    @Test
    public void testSearchButtonDisabledWhenLocationEmpty() {
        HomePage home = new HomePage();
        home.clickFindAStore();
        StoreLocatorPage locator = new StoreLocatorPage();
        locator.clickSelectMyStore();
        Assert.assertFalse(locator.isSearchButtonEnabled(), "Search button should be disabled when location is empty");
    }

    @AfterMethod
    public void tearDown() {
        DriverFactory.quitDriver();
    }
}
