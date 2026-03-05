package com.fl.automation.tests;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.openqa.selenium.WebDriver;
import com.fl.automation.core.DriverFactory;
import com.fl.automation.pages.HomePage;

/**
 * Acceptance Criteria ID: SCRUM-17166
 * Test Scenario ID: TS-001
 * Test Case ID: TC-001
 * Description: Launch app, Click Find a Store, Check for Select My Store link
 */
public class TS001_TC001_ {
    private WebDriver driver;
    private HomePage homePage;

    @BeforeMethod
    public void setUp() {
        driver = DriverFactory.getDriver();
        homePage = new HomePage(driver);
    }

    @Test
    public void testSelectMyStoreLinkPresent() {
        homePage.launch();
        homePage.clickFindStore();
        Assert.assertTrue(homePage.isSelectMyStoreLinkPresent(), "Select My Store link should be present");
    }

    @AfterMethod
    public void tearDown() {
        DriverFactory.quitDriver();
    }
}
