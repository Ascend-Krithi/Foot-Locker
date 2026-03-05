// Acceptance Criteria ID: SCRUM-17166
// Test Scenario ID: TS-006
// Test Case ID: TC-001
// Description: Verify store card contains address and 'Set My Store' button
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

public class TS006_TC001_3232 {
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
    public void verifyStoreCardContainsAddressAndSetMyStoreButton() {
        storeLocatorPage.searchStore("Dallas");
        storeResultsPage = new StoreResultsPage(driver);
        boolean found = false;
        for (WebElement card : storeResultsPage.getStoreCards()) {
            String address = storeResultsPage.getStoreAddress(card);
            boolean hasButton = storeResultsPage.isSetMyStoreButtonPresent(card);
            if (address != null && !address.trim().isEmpty() && hasButton) {
                found = true;
                break;
            }
        }
        Assert.assertTrue(found, "At least one store card should contain address and Set My Store button");
    }
}
