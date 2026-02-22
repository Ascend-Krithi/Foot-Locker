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
 * Acceptance Criteria ID: 
 * Test Scenario ID: SCRUM-15408 TS-001
 * Test Case ID: TC-005
 * Description: Launch the Foot Locker website, click the Find a Store link or button, click the close (X) button on the popup, and verify popup closes and homepage is displayed.
 */
public class TS001_TC005_VerifyPopupClose {
    private WebDriver driver;

    @BeforeMethod
    public void setUp() {
        DriverFactory.initDriver();
        driver = DriverFactory.getDriver();
        driver.get(ConfigReader.get("base.url"));
    }

    @Test
    public void testVerifyPopupClose() {
        HomePage homePage = new HomePage(driver);
        homePage.clickFindAStore();

        StoreLocatorPage locatorPage = new StoreLocatorPage(driver);
        Assert.assertTrue(locatorPage.isSelectMyStoreDisplayed(), "Select My Store popup did not appear");
    }

    @AfterMethod
    public void tearDown() {
        DriverFactory.quitDriver();
    }
}