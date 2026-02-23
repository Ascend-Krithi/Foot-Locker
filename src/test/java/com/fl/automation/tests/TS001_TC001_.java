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
 * Acceptance Criteria ID: 2163
 * Test Scenario ID: SCRUM-15408 TS-001
 * Test Case ID: TC-001
 * Description: Launch homepage, click 'Find a Store', verify popup and message
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
    public void testFindAStorePopupAppears() {
        homePage.open(ConfigReader.get("baseUrl"));
        homePage.clickFindAStore();
        Assert.assertTrue(storeLocatorPage.isPopupDisplayed(), "Popup message not displayed");
        Assert.assertTrue(storeLocatorPage.isSelectMyStoreVisible(), "'Select My Store' link not visible");
    }

    @AfterMethod
    public void tearDown() {
        DriverFactory.quitDriver();
    }
}
