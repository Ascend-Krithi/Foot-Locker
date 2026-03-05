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
Acceptance Criteria ID: 3193
Test Scenario ID: SCRUM-17166 TS-001
Test Case ID: TC-001
Description: Launch homepage, click Find a Store, verify popup and Select My Store link visible
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
    public void testFindAStorePopup() {
        HomePage homePage = new HomePage(driver);
        homePage.clickFindAStore();
        Assert.assertTrue(homePage.isFindAStorePopupDisplayed(), "Find a Store popup not displayed");
        Assert.assertTrue(homePage.isSelectMyStoreLinkVisible(), "Select My Store link not visible");
    }
    @AfterMethod
    public void tearDown() {
        DriverFactory.quitDriver();
    }
}
