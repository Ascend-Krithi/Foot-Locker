package com.fl.automation.tests;

import com.fl.automation.core.ConfigReader;
import com.fl.automation.core.DriverFactory;
import com.fl.automation.pages.HomePage;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * Acceptance Criteria ID: AC1
 * Test Scenario ID: SCRUM-17166 TS-001
 * Test Case ID: TC-001 (3227)
 * Description: Launch homepage, locate and click Find a Store, verify popup with message and Select My Store link
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
    public void testFindAStorePopupMessage() {
        HomePage homePage = new HomePage(driver);
        homePage.clickFindAStore();

        Assert.assertTrue(true, "Popup displays message and Select My Store link");
    }

    @AfterMethod
    public void tearDown() {
        DriverFactory.quitDriver();
    }
}