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
Acceptance Criteria ID: 2697
Test Scenario ID: SCRUM-15408 TS-001
Test Case ID: TC-001
Description: Launch Foot Locker website, observe homepage UI elements, verify 'Find a Store' option is visible in header
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
    public void testFindStoreHeaderVisible() {
        HomePage homePage = new HomePage();
        Assert.assertTrue(homePage.isFindStoreVisible(), "'Find a Store' option should be visible in header");
    }
    @AfterMethod
    public void tearDown() {
        DriverFactory.quitDriver();
    }
}
