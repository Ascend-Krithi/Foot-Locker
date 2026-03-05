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
 * Acceptance Criteria ID: AC6
 * Test Scenario ID: SCRUM-17166 TS-007
 * Test Case ID: TC-001 (3233)
 * Description: Launch homepage, set store, navigate to another page, verify store remains set
 */
public class TS007_TC001_3233 {

    private WebDriver driver;

    @BeforeMethod
    public void setUp() {
        DriverFactory.initDriver();
        driver = DriverFactory.getDriver();
        driver.get(ConfigReader.get("base.url"));
    }

    @Test
    public void testStoreSelectionPersistsAcrossPages() {
        HomePage homePage = new HomePage(driver);
        homePage.clickFindAStore();

        StoreLocatorPage locatorPage = new StoreLocatorPage(driver);
        locatorPage.clickSelectMyStore();

        StoreResultsPage resultsPage = new StoreResultsPage(driver);
        resultsPage.enterLocation("Boston, MA");
        resultsPage.clickSearchButton();
        resultsPage.clickSetMyStoreForAddress("375 Washington Street");

        driver.navigate().to(ConfigReader.get("base.url") + "/category");

        Assert.assertTrue(resultsPage.isConfirmationDisplayed(), "Selected store did not persist after navigation");
    }

    @AfterMethod
    public void tearDown() {
        DriverFactory.quitDriver();
    }
}