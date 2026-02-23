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
 * Test Case ID: TC-001 (2545)
 * Description: Launch homepage and verify Find a Store popup with message and link
 */
public class TS001_TC001_2545 {
    private WebDriver driver;

    @BeforeMethod
    public void setUp() {
        DriverFactory.initDriver();
        driver = DriverFactory.getDriver();
        driver.get(ConfigReader.get("base.url"));
    }

    @Test
    public void testFindAStorePopupDisplay() {
        HomePage homePage = new HomePage(driver);
        homePage.clickFindAStore();
        
        Assert.assertTrue(driver.getPageSource().contains("Choose a preferred store to make shopping easier"), 
                         "Popup should display the message");
        Assert.assertTrue(driver.getPageSource().contains("Select My Store"), 
                         "Select My Store link should be visible");
    }

    @AfterMethod
    public void tearDown() {
        DriverFactory.quitDriver();
    }
}