package com.fl.automation.tests;

import com.fl.automation.core.ConfigReader;
import com.fl.automation.core.DriverFactory;
import com.fl.automation.pages.HomePage;
import com.fl.automation.pages.StoreLocatorPage;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * Acceptance Criteria ID: 2171
 * Test Scenario ID: SCRUM-15408 TS-009
 * Test Case ID: TC-001
 * Description: Launch homepage -> Click Find a Store -> Click Select My Store -> Leave Location textbox empty -> Observe Search for Stores button is disabled
 */
public class TS009_TC001_TestCase_SCRUM15408_TS009_TC001 {
    private WebDriver driver;
    private HomePage homePage;
    private StoreLocatorPage storeLocatorPage;

    @BeforeMethod
    public void setUp() {
        DriverFactory.initDriver();
        driver = DriverFactory.getDriver();
        driver.get(ConfigReader.get("base.url"));
        homePage = new HomePage(driver);
        storeLocatorPage = new StoreLocatorPage(driver);
    }

    @Test
    public void testSearchButtonDisabledWhenLocationEmpty() {
        homePage.clickFindAStore();
        storeLocatorPage.clickSelectMyStore();
        Assert.assertFalse(storeLocatorPage.isSearchButtonEnabled(), "Search for Stores button should be disabled when location is empty");
    }

    @AfterMethod
    public void tearDown() {
        DriverFactory.quitDriver();
    }
}
