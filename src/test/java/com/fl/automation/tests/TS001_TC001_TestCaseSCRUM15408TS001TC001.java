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
 * Acceptance Criteria ID: 1646
 * Test Scenario ID: SCRUM-15408 TS-001
 * Test Case ID: TC-001
 * Description: Launch homepage, click Find a Store, verify popup message and Select My Store link visibility
 */
public class TS001_TC001_TestCaseSCRUM15408TS001TC001 {
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

        StoreLocatorPage storeLocatorPage = new StoreLocatorPage(driver);
        String popupMsg = storeLocatorPage.getPopupMessage();
        Assert.assertTrue(popupMsg.contains("Choose a preferred store to make shopping easier"),
                "Popup message should contain 'Choose a preferred store to make shopping easier'. Actual: " + popupMsg);

        Assert.assertTrue(storeLocatorPage.isSelectMyStoreLinkVisible(),
                "Select My Store link should be visible");
    }

    @AfterMethod
    public void tearDown() {
        DriverFactory.quitDriver();
    }
}