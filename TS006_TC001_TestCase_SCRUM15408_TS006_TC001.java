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
 * Test Scenario ID: TS-006
 * Test Case ID: TC-001
 * Description: Launch homepage, Click Find a Store, Click Select My Store, Enter Boston MA, Click Search, Locate store 375 Washington Street, Click Set My Store, Observe confirmation indicator
 */
public class TS006_TC001_TestCase_SCRUM15408_TS006_TC001 {
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
    public void testSetMyStoreAndConfirmation() {
        homePage.clickFindAStore();
        storeLocatorPage.clickSelectMyStore();
        storeLocatorPage.enterLocation("Boston MA");
        storeLocatorPage.clickSearchButton();
        Assert.assertTrue(storeResultsPage.setMyStoreByAddress("375 Washington Street"), "Set My Store button not found for address");
        Assert.assertTrue(storeResultsPage.isConfirmationIndicatorDisplayed(), "Confirmation indicator not displayed after setting store");
    }

    @AfterMethod
    public void tearDown() {
        DriverFactory.quitDriver();
    }
}
