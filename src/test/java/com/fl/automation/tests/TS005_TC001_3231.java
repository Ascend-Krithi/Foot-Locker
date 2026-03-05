// Acceptance Criteria ID: SCRUM-17166
// Test Scenario ID: TS-005
// Test Case ID: TC-001
// Description: Verify multiple store cards are displayed for broad search
package com.fl.automation.tests;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.openqa.selenium.WebDriver;
import com.fl.automation.core.DriverFactory;
import com.fl.automation.core.ConfigReader;
import com.fl.automation.pages.HomePage;
import com.fl.automation.pages.StoreLocatorPage;
import com.fl.automation.pages.StoreResultsPage;

public class TS005_TC001_3231 {
    private WebDriver driver;
    private StoreLocatorPage storeLocatorPage;
    private StoreResultsPage storeResultsPage;

    @BeforeMethod
    public void setUp() {
        driver = DriverFactory.getDriver();
        driver.get(ConfigReader.getProperty("baseUrl"));
        new HomePage(driver).clickFindStoreHeader();
        storeLocatorPage = new StoreLocatorPage(driver);
    }

    @AfterMethod
    public void tearDown() {
        DriverFactory.quitDriver();
    }

    @Test
    public void verifyMultipleStoreCardsForBroadSearch() {
        storeLocatorPage.searchStore("CA");
        storeResultsPage = new StoreResultsPage(driver);
        Assert.assertTrue(storeResultsPage.getStoreCards().size() > 1, "Multiple store cards should be displayed for broad search");
    }
}
