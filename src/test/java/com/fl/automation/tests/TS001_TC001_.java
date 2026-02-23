package com.fl.automation.tests;

import com.fl.automation.core.ConfigReader;
import com.fl.automation.core.DriverFactory;
import com.fl.automation.pages.HomePage;
import com.fl.automation.pages.StoreResultsPage;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * Acceptance Criteria ID: 2432
 * Test Scenario ID: SCRUM-15408 TS-001
 * Test Case ID: TC-001
 * Description: Verify Find a Store popup and Select My Store link
 */
public class TS001_TC001_ {
    private WebDriver driver;
    private HomePage homePage;
    private StoreResultsPage storeResultsPage;

    @BeforeMethod
    public void setUp() {
        driver = DriverFactory.getDriver();
        driver.get(ConfigReader.get("baseUrl"));
        homePage = new HomePage(driver);
        storeResultsPage = new StoreResultsPage(driver);
    }

    @Test
    public void testFindAStorePopupAndSelectMyStoreLink() {
        homePage.clickFindAStore();
        Assert.assertTrue(storeResultsPage.isSelectMyStoreLinkDisplayed(), "Select My Store link is not displayed in popup");
    }

    @AfterMethod
    public void tearDown() {
        DriverFactory.quitDriver();
    }
}
