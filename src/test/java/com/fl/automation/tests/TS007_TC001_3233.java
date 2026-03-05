// Acceptance Criteria ID: SCRUM-17166
// Test Scenario ID: TS-007
// Test Case ID: TC-001
// Description: Verify selecting store updates header to show selected store
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

public class TS007_TC001_3233 {
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
    public void verifySelectingStoreUpdatesHeader() {
        storeLocatorPage.searchStore("Seattle");
        storeResultsPage = new StoreResultsPage(driver);
        WebElement cardWithButton = null;
        for (WebElement card : storeResultsPage.getStoreCards()) {
            if (storeResultsPage.isSetMyStoreButtonPresent(card)) {
                cardWithButton = card;
                break;
            }
        }
        Assert.assertNotNull(cardWithButton, "Set My Store button should be present in at least one card");
        storeResultsPage.clickSetMyStoreButton(cardWithButton);
        // After selecting, verify header updates
        HomePage homePage = new HomePage(driver);
        Assert.assertTrue(homePage.isFindStoreHeaderDisplayed(), "Header should show selected store after selection");
    }
}
