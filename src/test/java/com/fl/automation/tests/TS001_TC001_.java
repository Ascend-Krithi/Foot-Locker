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
 * Test Scenario ID: SCRUM-17166 TS-001
 * Test Case ID: TC-001
 * Description: Launch homepage, click Find a Store, verify popup appears with message and Select My Store link is visible
 */
public class TS001_TC001_ {

    private WebDriver driver;

    @BeforeMethod
    public void setUp() {
        DriverFactory.initDriver();
        driver = DriverFactory.getDriver();
        driver.get(ConfigReader.get("base.url"));
    }

    @Test
    public void testFindStorePopupAndSelectMyStoreLink() {
        HomePage homePage = new HomePage(driver);
        homePage.clickFindAStore();

        StoreLocatorPage locatorPage = new StoreLocatorPage(driver);
        Assert.assertTrue(locatorPage.isSelectMyStoreDisplayed(), "Select My Store link is not visible");
    }

    @AfterMethod
    public void tearDown() {
        DriverFactory.quitDriver();
    }
}