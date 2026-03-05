// Acceptance Criteria ID: SCRUM-17166
// Test Scenario ID: TS-001
// Test Case ID: TC-006
// Description: Verify 'Set My Store' button is present inside store card
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

public class TS001_TC006_3198 {
    private WebDriver driver;
    private StoreResultsPage storeResultsPage;

    @BeforeMethod
    public void setUp() {
        driver = DriverFactory.getDriver();
        driver.get(ConfigReader.getProperty("baseUrl"));
        new HomePage(driver).clickFindStoreHeader();
        new StoreLocatorPage(driver).searchStore("Houston");
        storeResultsPage = new StoreResultsPage(driver);
    }

    @AfterMethod
    public void tearDown() {
        DriverFactory.quitDriver();
    }

    @Test
    public void verifySetMyStoreButtonPresentInCard() {
        boolean found = false;
        for (WebElement card : storeResultsPage.getStoreCards()) {
            if (storeResultsPage.isSetMyStoreButtonPresent(card)) {
                found = true;
                break;
            }
        }
        Assert.assertTrue(found, "Set My Store button should be present in at least one store card");
    }
}
