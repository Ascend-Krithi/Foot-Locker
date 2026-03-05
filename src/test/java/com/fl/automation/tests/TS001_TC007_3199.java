// Acceptance Criteria ID: SCRUM-17166
// Test Scenario ID: TS-001
// Test Case ID: TC-007
// Description: Verify clicking 'Set My Store' button marks store as selected
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

public class TS001_TC007_3199 {
    private WebDriver driver;
    private StoreResultsPage storeResultsPage;

    @BeforeMethod
    public void setUp() {
        driver = DriverFactory.getDriver();
        driver.get(ConfigReader.getProperty("baseUrl"));
        new HomePage(driver).clickFindStoreHeader();
        new StoreLocatorPage(driver).searchStore("Miami");
        storeResultsPage = new StoreResultsPage(driver);
    }

    @AfterMethod
    public void tearDown() {
        DriverFactory.quitDriver();
    }

    @Test
    public void verifySetMyStoreButtonMarksStoreSelected() {
        WebElement cardWithButton = null;
        for (WebElement card : storeResultsPage.getStoreCards()) {
            if (storeResultsPage.isSetMyStoreButtonPresent(card)) {
                cardWithButton = card;
                break;
            }
        }
        Assert.assertNotNull(cardWithButton, "Set My Store button should be present in at least one card");
        storeResultsPage.clickSetMyStoreButton(cardWithButton);
        // After clicking, verify some indicator (e.g., class change or text update)
        Assert.assertTrue(cardWithButton.getAttribute("class").contains("selected") ||
                          cardWithButton.getText().contains("My Store"),
                          "Store card should be marked as selected after clicking Set My Store");
    }
}
