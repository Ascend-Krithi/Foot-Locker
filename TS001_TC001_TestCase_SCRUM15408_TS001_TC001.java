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
 * Description: Launch Foot Locker website, Click Find a Store, Observe popup message and Select My Store link
 */
public class TS001_TC001_TestCase_SCRUM15408_TS001_TC001 {
    private WebDriver driver;
    private HomePage homePage;
    private StoreLocatorPage storeLocatorPage;

    @BeforeMethod
    public void setUp() {
        driver = DriverFactory.getDriver();
        driver.get(ConfigReader.get("base.url"));
        homePage = new HomePage(driver);
        storeLocatorPage = new StoreLocatorPage(driver);
    }

    @Test
    public void testFindAStorePopupAndSelectMyStore() {
        homePage.clickFindAStore();
        Assert.assertTrue(storeLocatorPage.isSearchButtonDisplayed(), "Store locator popup not displayed");
        Assert.assertTrue(storeLocatorPage.isLocationTextboxDisplayed(), "Select My Store link not displayed");
    }

    @AfterMethod
    public void tearDown() {
        DriverFactory.quitDriver();
    }
}
