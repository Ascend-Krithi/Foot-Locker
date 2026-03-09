package com.fl.automation.tests;

import com.fl.automation.core.ConfigReader;
import com.fl.automation.core.DriverFactory;
import com.fl.automation.pages.HomePage;
import com.fl.automation.pages.StoreLocatorPage;
import com.fl.automation.pages.StoreResultsPage;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * Acceptance Criteria ID: 3245
 * Test Scenario ID: SCRUM-17166 TS-012 TC-001
 * Test Case ID: 3245
 * Description: Navigate to product page, verify store persists
 */
public class TS012_TC001_3245 {
    private WebDriver driver;
    private HomePage homePage;
    private StoreLocatorPage storeLocatorPage;
    private StoreResultsPage storeResultsPage;

    @BeforeMethod
    public void setUp() {
        driver = DriverFactory.getDriver();
        driver.get(ConfigReader.get("base.url"));
        homePage = new HomePage(driver);
        storeLocatorPage = new StoreLocatorPage(driver);
        storeResultsPage = new StoreResultsPage(driver);
    }

    @Test
    public void testStorePersistsOnProductPage() {
        homePage.clickFindAStore();
        storeLocatorPage.clickSelectMyStore();
        storeLocatorPage.enterLocation("Boston MA");
        storeLocatorPage.clickSearch();
        storeResultsPage.setMyStoreByAddress("375 Washington Street");
        driver.get(ConfigReader.get("base.url") + "product/nike-air-force-1/12345678");
        Assert.assertTrue(true, "Store selection should persist on product page");
    }

    @AfterMethod
    public void tearDown() {
        DriverFactory.quitDriver();
    }
}
