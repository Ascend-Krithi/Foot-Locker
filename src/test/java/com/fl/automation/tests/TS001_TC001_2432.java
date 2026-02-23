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
 * Acceptance Criteria ID: SCRUM-15408
 * Test Scenario ID: TS-001
 * Test Case ID: TC-001 (2432)
 * Description: Foot Locker homepage, Find a Store popup verification
 */
public class TS001_TC001_2432 {
    private WebDriver driver;
    private HomePage homePage;

    @BeforeMethod
    public void setUp() {
        DriverFactory.initDriver();
        driver = DriverFactory.getDriver();
        driver.get(ConfigReader.get("baseUrl"));
        homePage = new HomePage(driver);
    }

    @Test
    public void verifyFindAStorePopup() {
        Assert.assertTrue(homePage.isFindStoreVisible(), "Find a Store link should be visible on homepage");
        homePage.clickFindStore();
        // Additional popup verification can be added here
    }

    @AfterMethod
    public void tearDown() {
        DriverFactory.quitDriver();
    }
}
