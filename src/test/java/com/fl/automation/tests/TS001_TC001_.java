package com.fl.automation.tests;

import com.fl.automation.core.ConfigReader;
import com.fl.automation.core.DriverFactory;
import com.fl.automation.pages.HomePage;
import com.fl.automation.pages.StoreLocatorPage;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.*;

/**
 * Acceptance Criteria ID: SCRUM-15408
 * Test Scenario ID: TS-001
 * Test Case ID: TC-001
 * Description: Launch homepage -> Click Find a Store -> Verify popup message 'Choose a preferred store to make shopping easier' -> Verify 'Select My Store' link is visible
 */
public class TS001_TC001_ {
    private WebDriver driver;
    private HomePage homePage;
    private StoreLocatorPage storeLocatorPage;

    @BeforeMethod
    public void setUp() {
        driver = DriverFactory.getDriver();
        homePage = new HomePage(driver);
        storeLocatorPage = new StoreLocatorPage(driver);
    }

    @Test
    public void testFindAStorePopupAndSelectMyStoreLink() {
        homePage.open(ConfigReader.get("baseUrl"));
        homePage.clickFindAStore();
        Assert.assertTrue(storeLocatorPage.isPopupMessageDisplayed(), "Popup message is not displayed");
        Assert.assertTrue(storeLocatorPage.isSelectMyStoreLinkVisible(), "'Select My Store' link is not visible");
    }

    @AfterMethod
    public void tearDown() {
        DriverFactory.quitDriver();
    }
}
