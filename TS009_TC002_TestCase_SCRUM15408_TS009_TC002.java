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
 * Test Scenario ID: TS-009
 * Test Case ID: TC-002
 * Description: Launch homepage, Click Find a Store, Click Select My Store, Leave Location textbox empty, If button enabled click it, Verify error message displayed
 */
public class TS009_TC002_TestCase_SCRUM15408_TS009_TC002 {
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
    public void testErrorMessageWhenSearchClickedWithEmptyLocation() {
        homePage.clickFindAStore();
        storeLocatorPage.clickSelectMyStore();
        if (storeLocatorPage.isSearchButtonEnabled()) {
            storeLocatorPage.clickSearchButton();
        }
        Assert.assertTrue(storeResultsPage.isNoStoresFoundMessageDisplayed(), "Error message not displayed when searching with empty location");
    }

    @AfterMethod
    public void tearDown() {
        DriverFactory.quitDriver();
    }
}
