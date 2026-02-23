package com.fl.automation.tests;

import com.fl.automation.core.ConfigReader;
import com.fl.automation.core.DriverFactory;
import com.fl.automation.pages.HomePage;
import com.fl.automation.pages.StoreLocatorPage;
import com.fl.automation.pages.StoreResultsPage;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.*;

/**
 * Acceptance Criteria ID: SCRUM-15408
 * Test Scenario ID: TS-007
 * Test Case ID: TC-001
 * Description: Launch homepage, Click Find a Store, Click Select My Store, Enter Boston MA, Click Search, Locate store 375 Washington Street, Click Set My Store, Navigate to another page, Verify preferred store remains set
 */
public class TS007_TC001_TestCase_SCRUM15408_TS007_TC001 {
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
    public void testPreferredStorePersists() {
        homePage.clickFindAStore();
        storeLocatorPage.clickSelectMyStore();
        storeLocatorPage.enterLocation("Boston MA");
        storeLocatorPage.clickSearchButton();
        Assert.assertTrue(storeResultsPage.setMyStoreByAddress("375 Washington Street"), "Set My Store button not found for address");
        Assert.assertTrue(storeResultsPage.isConfirmationIndicatorDisplayed(), "Confirmation indicator not displayed after setting store");
        driver.get(ConfigReader.get("base.url") + "/men");
        homePage = new HomePage(driver);
        homePage.clickFindAStore();
        Assert.assertTrue(storeResultsPage.isConfirmationIndicatorDisplayed(), "Preferred store not persisted after navigation");
    }

    @AfterMethod
    public void tearDown() {
        DriverFactory.quitDriver();
    }
}
