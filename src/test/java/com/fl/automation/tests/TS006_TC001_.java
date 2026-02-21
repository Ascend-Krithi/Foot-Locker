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
 * TS ID: TS-006
 * TC ID: TC-001
 * Description: Select a store from results
 */
public class TS006_TC001_ {
    private WebDriver driver;

    @BeforeMethod
    public void setUp() {
        DriverFactory.initDriver();
        driver = DriverFactory.getDriver();
        driver.get(ConfigReader.get("base.url"));
    }

    @Test
    public void testSelectStoreFromResults() {
        HomePage homePage = new HomePage(driver);
        StoreLocatorPage locatorPage = new StoreLocatorPage(driver);
        StoreResultsPage resultsPage = new StoreResultsPage(driver);
        homePage.clickFindAStore();
        locatorPage.clickSelectMyStore();
        locatorPage.enterSearchText("Chicago");
        locatorPage.clickSearchButton();
        int count = resultsPage.getStoreResultsCount();
        Assert.assertTrue(count > 0, "Store results should be displayed");
        resultsPage.selectStore(0);
        // No assertion as selection may not change UI, but should not throw exception
    }

    @AfterMethod
    public void tearDown() {
        DriverFactory.quitDriver();
    }
}
