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
Acceptance Criteria ID: AC1
Test Scenario ID: SCRUM-15408 TS-001
Test Case ID: TC-001
Description: Launch Foot Locker homepage, click Find a Store and verify popup with message and Select My Store link
*/
public class TS001_TC001_LaunchHomepageClickFindAStoreVerifyPopup {

    private WebDriver driver;

    @BeforeMethod
    public void setUp() {
        DriverFactory.initDriver();
        driver = DriverFactory.getDriver();
        driver.get(ConfigReader.get("base.url"));
    }

    @Test
    public void testLaunchHomepageClickFindAStoreVerifyPopup() {
        HomePage homePage = new HomePage(driver);
        homePage.clickFindAStore();
        
        StoreLocatorPage locatorPage = new StoreLocatorPage(driver);
        Assert.assertTrue(locatorPage.isPopupMessageDisplayed(), "Popup message not displayed");
        Assert.assertTrue(locatorPage.isSelectMyStoreDisplayed(), "Select My Store link not displayed");
    }

    @AfterMethod
    public void tearDown() {
        DriverFactory.quitDriver();
    }
}