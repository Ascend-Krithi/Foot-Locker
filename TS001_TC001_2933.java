package com.fl.automation.tests;

import com.fl.automation.core.DriverFactory;
import com.fl.automation.core.ConfigReader;
import com.fl.automation.pages.HomePage;
import com.fl.automation.pages.StoreLocatorPage;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * Acceptance Criteria ID: SCRUM-17166
 * Test Scenario ID: TS-001
 * Test Case ID: TC-001 (2933)
 * Description: Launch homepage -> Click Find a Store -> Verify popup message and Select My Store link
 */
public class TS001_TC001_2933 {
    private WebDriver driver;
    private HomePage homePage;
    private StoreLocatorPage storeLocatorPage;

    @BeforeMethod
    public void setUp() {
        driver = DriverFactory.getDriver();
        homePage = new HomePage(driver);
        storeLocatorPage = new StoreLocatorPage(driver);
    }

    @AfterMethod
    public void tearDown() {
        DriverFactory.quitDriver();
    }

    @Test
    public void testFindStorePopupAndSelectMyStoreLink() {
        homePage.launchHomePage(ConfigReader.getProperty("baseUrl"));
        homePage.clickFindStoreHeader();
        Assert.assertTrue(storeLocatorPage.getSelectMyStoreLink().isDisplayed(), "Select My Store link should be displayed in popup");
    }
}