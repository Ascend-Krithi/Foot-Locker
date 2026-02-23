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
Acceptance Criteria ID: 2432
Test Scenario ID: SCRUM-15408 TS-001 TC-001
Test Case ID: 2432
Description: Launch homepage, click 'Find a Store', verify popup and 'Select My Store' link
*/
public class TS001_TC001_2432 {
    private WebDriver driver;

    @BeforeMethod
    public void setUp() {
        DriverFactory.initDriver("chrome");
        driver = DriverFactory.getDriver();
        driver.get(ConfigReader.get("base.url"));
    }

    @Test
    public void testFindAStorePopupAndSelectMyStoreLink() {
        HomePage homePage = new HomePage(driver);
        homePage.clickFindAStore();
        Assert.assertTrue(homePage.isChoosePreferredStorePopupDisplayed(), "Popup message is not displayed");
        Assert.assertTrue(homePage.isSelectMyStoreLinkVisible(), "'Select My Store' link is not visible");
    }

    @AfterMethod
    public void tearDown() {
        DriverFactory.quitDriver();
    }
}
