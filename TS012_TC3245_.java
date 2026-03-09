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
 * Test Case ID: 3245
 * Test Case Name: Test Case - SCRUM-17166 TS-012 TC-001
 * Description: Verify store persistence on product page
 */
public class TS012_TC3245_ {
    private WebDriver driver;

    @BeforeMethod
    public void setUp() {
        DriverFactory.initDriver();
        driver = DriverFactory.getDriver();
        driver.get(ConfigReader.get("base.url"));
    }

    @Test
    public void testStorePersistenceOnProductPage() {
        HomePage homePage = new HomePage(driver);
        homePage.clickFindAStore();

        StoreLocatorPage locatorPage = new StoreLocatorPage(driver);
        locatorPage.clickSelectMyStore();
        locatorPage.enterLocation("Boston, MA");
        locatorPage.clickSearchButton();

        StoreResultsPage resultsPage = new StoreResultsPage(driver);
        resultsPage.clickSetMyStoreForAddress("375 Washington Street");

        driver.navigate().to(ConfigReader.get("base.url"));
        Assert.assertTrue(resultsPage.isConfirmationDisplayed(), "Store did not persist on product page");
    }

    @AfterMethod
    public void tearDown() {
        DriverFactory.quitDriver();
    }
}