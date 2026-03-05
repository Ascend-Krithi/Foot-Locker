// Acceptance Criteria ID: SCRUM-17166
// Test Scenario ID: TS-001
// Test Case ID: TC-005
// Description: Verify store address is present inside each store card
package com.fl.automation.tests;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import com.fl.automation.core.DriverFactory;
import com.fl.automation.core.ConfigReader;
import com.fl.automation.pages.HomePage;
import com.fl.automation.pages.StoreLocatorPage;
import com.fl.automation.pages.StoreResultsPage;

public class TS001_TC005_3197 {
    private WebDriver driver;
    private StoreResultsPage storeResultsPage;

    @BeforeMethod
    public void setUp() {
        driver = DriverFactory.getDriver();
        driver.get(ConfigReader.getProperty("baseUrl"));
        new HomePage(driver).clickFindStoreHeader();
        new StoreLocatorPage(driver).searchStore("Chicago");
        storeResultsPage = new StoreResultsPage(driver);
    }

    @AfterMethod
    public void tearDown() {
        DriverFactory.quitDriver();
    }

    @Test
    public void verifyStoreAddressPresentInEachCard() {
        for (WebElement card : storeResultsPage.getStoreCards()) {
            String address = storeResultsPage.getStoreAddress(card);
            Assert.assertNotNull(address, "Store address should be present in card");
            Assert.assertFalse(address.trim().isEmpty(), "Store address should not be empty");
        }
    }
}
