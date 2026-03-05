// Acceptance Criteria ID: SCRUM-17166
// Test Scenario ID: TS-001
// Test Case ID: TC-001
// Description: Verify Find Store header link is visible and clickable
package com.fl.automation.tests;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.openqa.selenium.WebDriver;
import com.fl.automation.core.DriverFactory;
import com.fl.automation.core.ConfigReader;
import com.fl.automation.pages.HomePage;

public class TS001_TC001_3193 {
    private WebDriver driver;
    private HomePage homePage;

    @BeforeMethod
    public void setUp() {
        driver = DriverFactory.getDriver();
        driver.get(ConfigReader.getProperty("baseUrl"));
        homePage = new HomePage(driver);
    }

    @AfterMethod
    public void tearDown() {
        DriverFactory.quitDriver();
    }

    @Test
    public void verifyFindStoreHeaderVisibleAndClickable() {
        Assert.assertTrue(homePage.isFindStoreHeaderDisplayed(), "Find Store header link should be visible");
        homePage.clickFindStoreHeader();
        Assert.assertTrue(driver.getCurrentUrl().contains("stores.footlocker.com"), "Should navigate to Store Locator page");
    }
}
