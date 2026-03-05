// Acceptance Criteria ID: SCRUM-17166
// Test Scenario ID: TS-001
// Test Case ID: TC-004
// Description: Verify store result cards are displayed after search
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

public class TS001_TC004_3196 {
    private WebDriver driver;
    private StoreResultsPage storeResultsPage;

    @BeforeMethod
    public void setUp() {
        driver = DriverFactory.getDriver();
        driver.get(ConfigReader.getProperty("baseUrl"));
        new HomePage(driver).clickFindStoreHeader();
        new StoreLocatorPage(driver).searchStore("Los Angeles");
        storeResultsPage = new StoreResultsPage(driver);
    }

    @AfterMethod
    public void tearDown() {
        DriverFactory.quitDriver();
    }

    @Test
    public void verifyStoreResultCardsDisplayed() {
        Assert.assertTrue(storeResultsPage.getStoreCards().size() > 0, "Store result cards should be displayed after search");
    }
}
