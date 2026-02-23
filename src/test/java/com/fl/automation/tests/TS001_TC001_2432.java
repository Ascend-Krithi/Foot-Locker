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
 * Description: Launch the Foot Locker homepage, click on 'Find a Store' link,
 * verify popup message and 'Select My Store' link visibility
 */
public class TS001_TC001_2432 {

    private WebDriver driver;

    @BeforeMethod
    public void setUp() {
        DriverFactory.initDriver();
        driver = DriverFactory.getDriver();
        driver.get(ConfigReader.get("base.url"));
    }

    @Test
    public void testFindAStorePopupAndSelectMyStore() {
        HomePage homePage = new HomePage(driver);
        homePage.clickFindAStore();

        Assert.assertTrue(homePage.isPopupMessageDisplayed(), "Popup message is not displayed");
        Assert.assertTrue(homePage.getPopupMessageText().contains("Choose a preferred store to make shopping easier"),
                "Popup message text does not match expected");

        StoreLocatorPage locatorPage = new StoreLocatorPage(driver);
        Assert.assertTrue(locatorPage.isSelectMyStoreVisible(), "Select My Store link is not visible");
    }

    @AfterMethod
    public void tearDown() {
        DriverFactory.quitDriver();
    }
}