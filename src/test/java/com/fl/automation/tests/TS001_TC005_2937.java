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
 * Test Case ID: 2937
 * Test Case Name: Test Case - SCRUM-17166 TS-001 TC-005
 * Description: Set the store at '375 Washington Street, Boston, Massachusetts, 02108' as 'My Store', observe the confirmation indicator (e.g., message, highlight, or store name) for store selection, and verify confirmation indicator is displayed confirming store selection and the selected store appears consistently across the website (e.g., header or location indicator).
 */
public class TS001_TC005_2937 {

    private WebDriver driver;

    @BeforeMethod
    public void setUp() {
        DriverFactory.initDriver();
        driver = DriverFactory.getDriver();
        driver.get(ConfigReader.get("base.url"));
    }

    @Test
    public void testStoreSelectionConfirmationIndicator() {
        HomePage homePage = new HomePage(driver);
        homePage.clickFindAStore();

        StoreLocatorPage locatorPage = new StoreLocatorPage(driver);
        locatorPage.clickSelectMyStore();

        StoreResultsPage resultsPage = new StoreResultsPage(driver);
        resultsPage.enterLocation("Boston, MA");
        resultsPage.clickSearchButton();

        resultsPage.clickSetMyStoreForAddress("375 Washington Street, Boston, Massachusetts, 02108");

        Assert.assertTrue(resultsPage.isStoreSetConfirmationDisplayed(), "Confirmation indicator not displayed");
    }

    @AfterMethod
    public void tearDown() {
        DriverFactory.quitDriver();
    }
}