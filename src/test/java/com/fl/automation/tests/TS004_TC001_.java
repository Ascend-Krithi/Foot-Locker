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
 * AC ID: SCRUM-15408
 * TS ID: TS-004
 * TC ID: TC-001
 * Description: Search for stores by city name
 */
public class TS004_TC001_ {
    private WebDriver driver;

    @BeforeMethod
    public void setUp() {
        DriverFactory.initDriver();
        driver = DriverFactory.getDriver();
        driver.get(ConfigReader.get("base.url"));
    }

    @Test
    public void testSearchByCityName() {
        HomePage homePage = new HomePage(driver);
        StoreLocatorPage locatorPage = new StoreLocatorPage(driver);
        StoreResultsPage resultsPage = new StoreResultsPage(driver);
        homePage.clickFindAStore();
        locatorPage.clickSelectMyStore();
        locatorPage.enterSearchText("New York");
        locatorPage.clickSearchButton();
        Assert.assertTrue(resultsPage.getStoreResultsCount() > 0, "Should display store results for city name");
    }

    @AfterMethod
    public void tearDown() {
        DriverFactory.quitDriver();
    }
}
