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
 * Test Scenario ID: SCRUM-15408 TS-001
 * Test Case ID: TC-001
 * Description: Launch the Foot Locker website, click on Find a Store link, and verify popup message and Select My Store link visibility
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
    public void testFindAStorePopupAndSelectMyStoreLink() {
        HomePage homePage = new HomePage(driver);
        homePage.clickFindAStore();

        StoreLocatorPage locatorPage = new StoreLocatorPage(driver);
        String popupMessage = locatorPage.getPopupMessage();
        Assert.assertTrue(popupMessage.contains("Choose a preferred store to make shopping easier"), "Popup message not displayed correctly");
        Assert.assertTrue(locatorPage.isSelectMyStoreDisplayed(), "Select My Store link not visible");
    }

    @AfterMethod
    public void tearDown() {
        DriverFactory.quitDriver();
    }
}