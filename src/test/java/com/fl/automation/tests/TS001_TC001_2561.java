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
 * Test Scenario ID: SCRUM-15408 TS-001
 * Test Case ID: TC-001 (2561)
 * Description: Launch homepage, click Find a Store and verify popup content
 */
public class TS001_TC001_2561 {
    private WebDriver driver;

    @BeforeMethod
    public void setUp() {
        DriverFactory.initDriver();
        driver = DriverFactory.getDriver();
        driver.get(ConfigReader.get("base.url"));
    }

    @Test
    public void testFindAStorePopupContent() {
        HomePage homePage = new HomePage(driver);
        homePage.clickFindAStore();
        
        Assert.assertTrue(driver.getPageSource().contains("Choose a preferred store to make shopping easier") && 
                         driver.getPageSource().contains("Select My Store"), 
                         "Popup displays the message and a visible Select My Store link");
    }

    @AfterMethod
    public void tearDown() {
        DriverFactory.quitDriver();
    }
}