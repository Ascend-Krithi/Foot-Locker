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
 * Acceptance Criteria ID: AC1
 * Test Scenario ID: SCRUM-17166 TS-001
 * Test Case ID: TC-001 (3227)
 * Description: Launch homepage, click Find a Store button, verify popup message and Select My Store link
 */
public class TS001_TC001_3227 {

    private WebDriver driver;

    @BeforeMethod
    public void setUp() {
        DriverFactory.initDriver();
        driver = DriverFactory.getDriver();
        driver.get(ConfigReader.get("base.url"));
    }

    @Test
    public void testFindAStoreButtonAndPopup() {
        HomePage homePage = new HomePage(driver);
        Assert.assertTrue(homePage.isFindAStoreDisplayed(), "Find a Store button not visible");
        homePage.clickFindAStore();

        StoreLocatorPage locatorPage = new StoreLocatorPage(driver);
        Assert.assertTrue(locatorPage.isSelectMyStoreDisplayed(), "Select My Store link not displayed in popup");
    }

    @AfterMethod
    public void tearDown() {
        DriverFactory.quitDriver();
    }
}