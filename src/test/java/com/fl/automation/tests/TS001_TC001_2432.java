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
 * Test Case ID: TC-001 (2432)
 * Description: Verify 'Find a Store' popup and 'Select My Store' link visibility
 */
public class TS001_TC001_2432 {
    private WebDriver driver;
    private HomePage homePage;
    private StoreLocatorPage storeLocatorPage;

    @BeforeMethod
    public void setUp() {
        DriverFactory.initDriver();
        driver = DriverFactory.getDriver();
        homePage = new HomePage(driver);
        storeLocatorPage = new StoreLocatorPage(driver);
    }

    @Test
    public void testFindAStorePopupAndSelectMyStoreLink() {
        homePage.open(ConfigReader.get("baseUrl"));
        homePage.clickFindAStore();
        Assert.assertTrue(storeLocatorPage.isPopupMessageDisplayed(), "Popup message not displayed");
        Assert.assertTrue(storeLocatorPage.isSelectMyStoreLinkVisible(), "'Select My Store' link not visible");
    }

    @AfterMethod
    public void tearDown() {
        DriverFactory.quitDriver();
    }
}
