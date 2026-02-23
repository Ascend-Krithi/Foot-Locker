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
 * Test Case ID: TC-001 (2432)
 * Description: Launch homepage, navigate to Find a Store, verify popup message and Select My Store link
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
    public void testFindAStorePopupVerification() {
        HomePage homePage = new HomePage(driver);
        homePage.clickFindAStore();

        Assert.assertTrue(homePage.isPopupMessageDisplayed(), "Popup message 'Choose a preferred store to make shopping easier' not displayed");

        StoreLocatorPage locatorPage = new StoreLocatorPage(driver);
        Assert.assertTrue(locatorPage.isSelectMyStoreVisible(), "Select My Store link not visible");
    }

    @AfterMethod
    public void tearDown() {
        DriverFactory.quitDriver();
    }
}